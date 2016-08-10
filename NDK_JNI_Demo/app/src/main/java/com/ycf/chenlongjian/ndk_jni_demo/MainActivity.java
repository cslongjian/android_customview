package com.ycf.chenlongjian.ndk_jni_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView helloJni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helloJni = (TextView)findViewById(R.id.jni);
        NdkJniUtils jniUtils = new NdkJniUtils();
        String text = jniUtils.getCLanguageString();
        helloJni.setText(text);
    }
}


