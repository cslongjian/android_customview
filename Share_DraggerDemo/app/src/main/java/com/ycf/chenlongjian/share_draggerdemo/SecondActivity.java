package com.ycf.chenlongjian.share_draggerdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ycf.chenlongjian.share_draggerdemo.component.MainComponent;

import javax.inject.Inject;

/**
 * Created by chenlongjian on 2016/9/5.
 */
public class SecondActivity  extends AppCompatActivity {

    //添加@Inject注解，表示这个mPoetry是需要注入的
    @Inject
    Cat mMyCat;

    @Inject
    Gson mGson;

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setTitle("secondActivity");

        MainComponent.getInstance().inject(this);

        initView();
    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.catname);
        String json = mGson.toJson(mMyCat);
        String text = json + ",mMyCat:"+mMyCat;
        mTextView.setText(text);
    }
}
