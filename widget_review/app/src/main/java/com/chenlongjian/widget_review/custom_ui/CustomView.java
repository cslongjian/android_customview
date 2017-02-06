package com.chenlongjian.widget_review.custom_ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.chenlongjian.widget_review.utils.MeasureUtil;

/**
 * 自定义view
 * Created by chenlongjian on 2017/2/4.
 */

public class CustomView extends View implements Runnable {

    private Paint mPaint;
    private Context mContext;// 上下文环境引用

    private int radiu;// 圆环半径

    //默认方法
    public CustomView(Context context) {
        super(context);
    }

    //XML配置需要 属性方法
    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.mContext = context;

        // 初始化画笔
        initPaint();

    }


    /**
     * 初始化画笔
     */
    private void initPaint() {
        // 实例化画笔并打开抗锯齿
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    /*
     * 设置画笔样式为描边，圆环嘛……当然不能填充不然就么意思了
     *
     * 画笔样式分三种：
     * 1.Paint.Style.STROKE：描边
     * 2.Paint.Style.FILL_AND_STROKE：描边并填充
     * 3.Paint.Style.FILL：填充
     */
        mPaint.setStyle(Paint.Style.STROKE);

        // 设置画笔颜色为浅灰色
        mPaint.setColor(Color.LTGRAY);

    /*
     * 设置描边的粗细，单位：像素px
     * 注意：当setStrokeWidth(0)的时候描边宽度并不为0而是只占一个像素
     */
        mPaint.setStrokeWidth(10);

    }

    //步骤1 测量大小
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    //步骤2 布局位置
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


    //步骤3 绘制
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //反复创建和绘制的地方。这里不能进行创建对象。不然内存暴涨
//        Paint paint = new Paint();
//        paint.setAntiAlias(true);

        // 绘制圆环
        canvas.drawCircle(MeasureUtil.getScreenSize((Activity) mContext)[0] / 2, MeasureUtil.getScreenSize((Activity) mContext)[1] / 2, radiu, mPaint);

    }

    @Override
    public void run() {
  /*
         * 确保线程不断执行不断刷新界面
         */
        while (true) {
            try {
                /*
                 * 如果半径小于200则自加否则大于200后重置半径值以实现往复
                 */
                if (radiu <= 200) {
                    radiu += 10;

                    // 刷新View
                    postInvalidate();
                } else {
                    radiu = 0;
                }

                // 每执行一次暂停40毫秒
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
