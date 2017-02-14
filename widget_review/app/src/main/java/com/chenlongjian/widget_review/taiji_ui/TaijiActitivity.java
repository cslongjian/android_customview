package com.chenlongjian.widget_review.taiji_ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Created by chenlongjian on 2017/2/14.
 */

public class TaijiActitivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_taiji);

        final TaijiView taiJi = new TaijiView(this);
        setContentView(taiJi);
        final Handler handler = new Handler() {
            private float degrees = 0;
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                taiJi.setRotate(degrees += 5);
                this.sendEmptyMessageDelayed(0, 80);
            }
        };

        handler.sendEmptyMessageDelayed(0, 20);
    }
}
