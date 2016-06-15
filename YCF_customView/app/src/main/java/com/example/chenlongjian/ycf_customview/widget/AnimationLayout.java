package com.example.chenlongjian.ycf_customview.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chenlongjian.ycf_customview.R;
import com.example.chenlongjian.ycf_customview.common.UIHelper;
import com.example.chenlongjian.ycf_customview.model.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenlongjian on 2016/6/15.
 */
public class AnimationLayout extends LinearLayout {

    private LinearLayout mContainerLayout;
    private View mTapView;
    private List<TextView> mSortViewList = new ArrayList<>();

    public static final int SORT_RECOMMEND = 0;
    public static final int SORT_PRICE = 1;
    public static final int SORT_SALE = 2;
    public static final int SORT_ME = 3;

    public AnimationLayout(Context context) {
        super(context);
        initLayout();
    }

    public AnimationLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initLayout();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public AnimationLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AnimationLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initLayout();
    }

    public void initLayout(){
        inflate(getContext(), R.layout.animation_layout, this);
        mContainerLayout = (LinearLayout) findViewById(R.id.layout_sort_filter);
//        mTapView = findViewById(R.id.view_tap);
//        mTapView.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                hide();
//            }
//        });
//
        initSort(initSortList());
    }

    public void initSort(List<Sort> sorts){
        if(null == sorts || 0 == sorts.size()){
            return;
        }
        mSortViewList = new ArrayList<>();
        mContainerLayout.removeAllViews();
        int padding = UIHelper.dip2px(getContext(), 12f);
        for(int i = 0;i < sorts.size(); i ++){
            Sort sort = sorts.get(i);
            TextView sortView = new TextView(getContext());
            sortView.setGravity(Gravity.CENTER_VERTICAL);
            sortView.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, UIHelper.dip2px(getContext(), 48)));
            sortView.setId(sort.getId());
            sortView.setText(sort.getName());
            sortView.setTextSize(15f);
            sortView.setTextColor(Color.parseColor("#8b7a69"));
            sortView.setPadding(padding, padding, padding, padding);
//            sortView.setBackgroundResource(R.drawable.detial_item_bg);
            mContainerLayout.addView(sortView);
            View lineView = new View(getContext());
            LinearLayout.LayoutParams lineLP = new LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 1);
            if(i != sorts.size() - 1){
                lineLP.leftMargin = padding;
            }
            lineView.setBackgroundColor(Color.parseColor("#dee1e5"));
            lineView.setLayoutParams(lineLP);
            mContainerLayout.addView(lineView);
            mSortViewList.add(sortView);
        }
    }

    protected List<Sort> initSortList() {
        List<Sort> sortList = new ArrayList<>();
        Sort sort = new Sort();
        sort.setId(AnimationLayout.SORT_RECOMMEND);
        sort.setName("推荐排序");
        sortList.add(sort);
        sort = new Sort();
        sort.setId(AnimationLayout.SORT_PRICE);
        sort.setName("价格最低");
        sortList.add(sort);
        sort = new Sort();
        sort.setId(AnimationLayout.SORT_SALE);
        sort.setName("销量优先");
        sortList.add(sort);
        sort = new Sort();
        sort.setId(AnimationLayout.SORT_ME);
        sort.setName("离我最近");
        sortList.add(sort);
        return sortList;
    }

    public void hide()
    {

    }

}
