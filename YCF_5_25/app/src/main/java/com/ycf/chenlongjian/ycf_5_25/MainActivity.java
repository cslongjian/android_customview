package com.ycf.chenlongjian.ycf_5_25;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ycf.chenlongjian.ycf_5_25.myjstestdemo.WebViewJsTestActvity;

public class MainActivity extends AppCompatActivity {

    private Button mKeyboardbtn;

    private Button mJsbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mKeyboardbtn = (Button)findViewById(R.id.keyboardbtn);
        mKeyboardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, KeyboardTestActivity.class);
                startActivity(intent);
            }
        });

        mJsbtn = (Button)findViewById(R.id.jsbtn);
        mJsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebViewJsTestActvity.class);
                startActivity(intent);
            }
        });

    }
}
