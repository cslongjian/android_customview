package com.ycf.chenlongjian.ycf_5_40;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private CompenentBtn normal;
    private CompenentBtn mianyuyue;
    private CompenentBtn presale;
    private CompenentBtn grouppresale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        normal = (CompenentBtn) findViewById(R.id.normal);
        normal.setType(CompenentBtn.TYPE_NORMAL);

        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.w("MotionEvent","MotionEventMotionEventMotionEventMotionEventMotionEvent");
            }
        });

        mianyuyue = (CompenentBtn) findViewById(R.id.mianyuyue);
        mianyuyue.setType(CompenentBtn.TYPE_MIANYUYUE);
        mianyuyue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.w("MotionEvent","MotionEventMotionEventMotionEventMotionEventMotionEvent");
            }
        });

        presale = (CompenentBtn) findViewById(R.id.presale);
        presale.setType(CompenentBtn.TYPE_PRESALE);
        presale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.w("MotionEvent","MotionEventMotionEventMotionEventMotionEventMotionEvent");
            }
        });

        grouppresale = (CompenentBtn) findViewById(R.id.grouppresale);
        grouppresale.setType(CompenentBtn.TYPE_GROUPPRESALE);
        grouppresale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.w("MotionEvent","MotionEventMotionEventMotionEventMotionEventMotionEvent");
            }
        });


    }
}
