package com.example.chenlongjian.ycf_customview.widget_ycf.rollbanner;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.chenlongjian.ycf_customview.R;

/**
 * Created by chenlongjian on 16/6/30.
 */
public class RollBannerLayout extends LinearLayout {

    private ViewGroup mRollContainer;

    private ViewPager mVpRollBanner;


    //复写控件的标准构造方法两个
    public RollBannerLayout(Context context)
    {
        super(context);
        init(context);
    }

    public RollBannerLayout(Context context, AttributeSet attributeSet)
    {
        super(context, attributeSet);
        init(context);
    }

    private void init(Context ctx)
    {
        View.inflate(ctx,R.layout.widget_rollbanner_layout, this);

        mRollContainer = (ViewGroup) findViewById(R.id.rollcontainer);

        mVpRollBanner = (ViewPager) findViewById(R.id.rollbanner);

    }

    public void bindBanner()
    {
        mVpRollBanner.setAdapter(new ScrollBannerAdapter(getContext(), scrollBannerList));
        mIsPalyBanner = true;
        mRollContainer.setVisibility(View.VISIBLE);
        mRollIndicator.setViewPager(mVpRollBanner, 0, scrollBannerList.size());
        if (mAutoScrollHandler.hasMessages(MSG_BANNER_PLAY)) {
            mAutoScrollHandler.removeMessages(MSG_BANNER_PLAY);

    }





}
