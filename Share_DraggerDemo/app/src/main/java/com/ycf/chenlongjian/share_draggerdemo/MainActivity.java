package com.ycf.chenlongjian.share_draggerdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ycf.chenlongjian.share_draggerdemo.component.DaggerDogComponent;
import com.ycf.chenlongjian.share_draggerdemo.component.MainComponent;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    //添加@Inject注解，表示这个mMyCat是需要注入的
    @Inject
    Cat mMyCat;


    @Inject
    Gson mGson;

    @Inject
    Dog mMyDog;



    private TextView mTextView;
    private TextView mDogTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 使用Dagger2生成的类 生成组件进行构造，并注入
        DaggerDogComponent.builder().build().inject(this);

        MainComponent.getInstance().inject(this);

        initView();
    }

    void initView()
    {
        mTextView = (TextView) findViewById(R.id.catname);
        String json = mGson.toJson(mMyCat);
        String text = json + ",mMyCat:"+mMyCat;
        mTextView.setText(text);

        findViewById(R.id.open).setOnClickListener(view ->
                startActivity(new Intent(this,SecondActivity.class)));

        findViewById(R.id.opencontain).setOnClickListener(view ->
                startActivity(new Intent(this,ContainActivity.class)));


        mDogTextView = (TextView) findViewById(R.id.dogname);
        String dogjson = mGson.toJson(mMyDog);
        mDogTextView.setText(dogjson);
//
    }

}
