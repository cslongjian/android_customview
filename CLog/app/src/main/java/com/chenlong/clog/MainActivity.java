package com.chenlong.clog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.animation.ObjectAnimator;
import android.animation.Animator;

import com.chenlong.clog.util.CLog;
import com.chenlong.clog.widget.LineView;

public class MainActivity extends AppCompatActivity {

    private Animator animator;
    private LineView lineView;
    private int width;
    private int windowWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        windowWidth = this.getWindowManager().getDefaultDisplay().getWidth();

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimator();
            }
        });

        lineView = (LineView) findViewById(R.id.lineView);
        ViewTreeObserver observer = lineView.getViewTreeObserver();
        observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                width = lineView.getMeasuredWidth();
                CLog.d("width:" + width);

                if (width == 0 )
                {
                    CLog.d("调用停取消");
//                    animator.resume();
                    lineView.setLayoutWidth(windowWidth);

                }
                return true;
            }
        });

        testMethod();

    }

    private void startAnimator()
    {
        animator = ObjectAnimator.ofInt(lineView, "layoutWidth", width, 0);
        animator.setDuration(6000);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();
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
