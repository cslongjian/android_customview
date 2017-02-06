package com.chenlongjian.widget_review;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.chenlongjian.widget_review.activity.Custom2Activity;
import com.chenlongjian.widget_review.custom_ui.CustomView;
import com.chenlongjian.widget_review.system_ui.ListViewActivity;

public class MainActivity extends AppCompatActivity {


    private CustomView customView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        customView = (CustomView)findViewById(R.id.customview);

         /*
         * 开线程
         */
        new Thread(customView).start();

        findViewById(R.id.listview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ListViewActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.custom_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Custom2Activity.class);
                startActivity(intent);
            }
        });

    }
}
