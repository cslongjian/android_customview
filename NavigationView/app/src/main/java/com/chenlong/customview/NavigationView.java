package com.chenlong.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chenlong.activity.navigationview.R;

/**
 * Created by supwin_mbp002 on 16/4/14.
 */
public class NavigationView extends RelativeLayout implements View.OnClickListener {

    private ImageView backView;
    private ImageView rightView;
    private TextView titleView;
    private ClickCallBack callBack;

    public ImageView getBackView() {
        return backView;
    }

    public TextView getTitleView() {
        return titleView;
    }

    public void setTitle(String title) {
        titleView.setText(title);
    }

    public ImageView getRightView() {
        return rightView;
    }

    public void setClickCallBack(ClickCallBack callback) {
        this.callBack = callback;
    }


    public NavigationView(Context context) {
        super(context, null);
    }

    public NavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.customnavigationview, this, true);
        backView = (ImageView) view.findViewById(R.id.iv_nav_back);
        backView.setOnClickListener(this);
        titleView = (TextView) view.findViewById(R.id.tv_nav_title);
        rightView = (ImageView) view.findViewById(R.id.iv_nav_right);
        rightView.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_nav_back) {
            callBack.onBackClick();
        }

        if (id == R.id.iv_nav_right) {
            callBack.onRightClick();
        }
    }

    public static interface ClickCallBack {
        void onBackClick();

        void onRightClick();
    }


}
