package com.example.chenlongjian.ycf_customview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

import com.example.chenlongjian.ycf_customview.R;

/**
 * Created by chenlongjian on 2016/6/12.
 */
public class TestLayout extends HorizontalScrollView {

    public TestLayout(Context context) {
        super(context);
        initLayout();
    }

    public TestLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout();
    }

    private void initLayout(){
        inflate(getContext(), R.layout.layout_test,this);

    }
}
