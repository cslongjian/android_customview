package com.example.chenlongjian.ycf_customview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.chenlongjian.ycf_customview.demand5_24.Demo5_24Activity;
import com.example.chenlongjian.ycf_customview.widget.DateChooseLayout;
import com.example.chenlongjian.ycf_customview.widget_ycf.rollbanner.RollBannerActivity;

public class MainActivity extends AppCompatActivity implements DateChooseLayout.OnItemChooseListener {

    private DateChooseLayout dateChooseLayout;

    private Button mBtn;

    private Button mBtn2;

    private Button text1;//半透明测试

    private Button text2; //半透明测试

    private boolean mCheck; //标记

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        dateChooseLayout = (DateChooseLayout)findViewById(R.id.choose_date);
//        dateChooseLayout.setOnItemChooseListener(this);
//        dateChooseLayout.setSaveChooseFlag(true);

        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),DialogActivity.class);
                startActivity(intent);
            }
        });

        mBtn2 = (Button) findViewById(R.id.btn2);
        mBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),Demo5_24Activity.class);
                startActivity(intent);
            }
        });



        text1 = (Button) findViewById(R.id.text);
        text1.setClickable(true);
        text1.getBackground().setAlpha(0);
        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, RollBannerActivity.class);
                startActivity(intent);
                Log.w("半透明测试","当前状态："+mCheck);
                if (mCheck)
                {
                    text2.setTextColor(0x7ff8881a);
                    text2.getBackground().setAlpha(0);

                }else
                {
                    text2.setTextColor(0xfff8881a);
                    text2.getBackground().setAlpha(0);
                }
                mCheck = !mCheck;
            }
        });

        text2 = (Button) findViewById(R.id.text2);

//        text2.getBackground().setAlpha(254);//0~255透明度值 ();

//        text2.setTextColor(0x7ff8881a);
        text2.getBackground().setAlpha(0);


    }

    @Override
    public void onItemChoose(String date) {

    }

    @Override
    public void chooseAction() {

    }

    @Override
    public void onHideMask() {

    }

    @Override
    public void onShowMask() {

    }
}
