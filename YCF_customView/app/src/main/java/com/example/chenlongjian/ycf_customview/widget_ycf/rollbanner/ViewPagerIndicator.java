package com.example.chenlongjian.ycf_customview.widget_ycf.rollbanner;

/**
 * Created by chenlongjian on 16/6/30.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import com.example.chenlongjian.ycf_customview.R;
import com.example.chenlongjian.ycf_customview.common.UIHelper;

/**
 * ViewPager使用的指示器
 */
public class ViewPagerIndicator extends RadioGroup implements ViewPager.OnPageChangeListener {

    private static final int BASEID = 0x5201314;
    private ViewPager mViewPager;
    private int mViewPagerSize;
    private List<RadioButton> radioList = new ArrayList<>();
    private float mSpace;

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ViewPagerIndicator(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context ctx, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = ctx.getTheme().obtainStyledAttributes(attrs,
                    R.styleable.ViewPagerIndicator, 0, 0);
            try {
                mSpace = a.getDimension(R.styleable.ViewPagerIndicator_circlespace, 1);
            } finally {
                a.recycle();
            }
        }
        setOrientation(RadioGroup.HORIZONTAL);
    }

    private void initRadioList() {
        radioList.clear();
        for (int i = 0; i < 10; i++) {
            RadioButton rb = new RadioButton(getContext());
            rb.setButtonDrawable(R.drawable.banner_radio_selector);
            rb.setPadding(0, 0, 0, 0);
            android.widget.RadioGroup.LayoutParams perParams = new RadioGroup.LayoutParams(
                    UIHelper.dip2px(getContext(), 7), UIHelper.dip2px(
                    getContext(), 7));
            perParams.leftMargin = (int) mSpace;
            perParams.rightMargin = (int) mSpace;
            perParams.gravity = Gravity.CENTER_HORIZONTAL;
            rb.setLayoutParams(perParams);
            int id = BASEID + i;
            rb.setId(id);
            radioList.add(rb);
        }
    }

    public void setViewPager(ViewPager view, int initIndex, int pageSize) {
        initRadioList();
        mViewPager = view;
        mViewPagerSize = pageSize;
        if (pageSize == 1) {
            setVisibility(GONE);
            return;
        }
        removeAllViews();
        setVisibility(VISIBLE);
        setWeightSum(pageSize);
        for (int i = 0; i < pageSize; i++) {
            if (i == initIndex)
                radioList.get(i).setChecked(true);
            addView(radioList.get(i));
        }
        mViewPager.removeOnPageChangeListener(this);

        mViewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int index = position % mViewPagerSize;
        check(BASEID + index);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}