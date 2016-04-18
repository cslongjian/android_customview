package com.chenlong.clog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chenlong.clog.util.CLog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CLog.d("Test ddddddd");
        CLog.e("Test eeeeee");
        CLog.v("Test vvvvvv");
        CLog.i("Test iiiiiii");
        CLog.w("Test wwwwwww");

        testMethod();

    }

    private void testMethod()
    {

        CLog.d("Test ddddddd");
        CLog.e("Test eeeeee");
        CLog.v("Test vvvvvv");
        CLog.i("Test iiiiiii");
        CLog.w("Test wwwwwww");

    }

}
