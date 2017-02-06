package com.chenlongjian.widget_review.custom_ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.chenlongjian.widget_review.R;
import com.chenlongjian.widget_review.utils.MeasureUtil;

/**
 * Created by chenlongjian on 2017/2/4.
 */

public class CustomView_2 extends View {

    private Paint mPaint;
    private Context mContext;// 上下文环境引用

//    private int radiu;// 圆环半径

    private int x,y;// 位图绘制时左上角的起点坐标
    private Bitmap bitmap;// 位图

    //默认方法
    public CustomView_2(Context context) {
        super(context);
    }

    //XML配置需要 属性方法
    public CustomView_2(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.mContext = context;

        // 初始化画笔
        initPaint();

        //初始化资源
        initRes(context);

    }



    /**
     * 初始化画笔
     */
    private void initPaint() {
        // 实例化画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

//        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
//                0.33F, 0.59F, 0.11F, 0, 0,
//                0.33F, 0.59F, 0.11F, 0, 0,
//                0.33F, 0.59F, 0.11F, 0, 0,
//                0, 0, 0, 1, 0,
//        });

//        原色
        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                1, 0, 0, 0, 0,
                0, 1, 0, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 0, 1, 0,
        });

        // 设置颜色过滤  过滤绿色
        mPaint.setColorFilter(new LightingColorFilter(0xFFFF00FF, 0x00000000));

//        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
    }

    /**
     * 初始化画笔
     */
    private void initPaint2() {
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
        mPaint.setStyle(Paint.Style.FILL);

        // 设置画笔颜色 自定义色彩
        mPaint.setColor(Color.argb(255, 255, 128, 103));


        // 生成色彩矩阵 1为元色彩
        /**
         * 其中，第一行表示的R（红色）的向量，第二行表示的G（绿色）的向量，
         * 第三行表示的B（蓝色）的向量，最后一行表示A（透明度）的向量，
         * 这一顺序必须要正确不能混淆！这个矩阵不同的位置表示的RGBA值，
         * 其范围在0.0F至2.0F之间，1为保持原图的RGB值。每一行的第五列数字表示偏移值，
         * 何为偏移值？
         * 顾名思义当我们想让颜色更倾向于红色的时候就增大R向量中的偏移值，
         * 想让颜色更倾向于蓝色的时候就增大B向量中的偏移值，这是最最朴素的理解，但是事实上色彩偏移的概念是基于白平衡来理解的，
         * 什么是白平衡呢？说得简单点就是白色是什么颜色！
         * 如果大家是个单反爱好者或者会些PS就会很容易理解这个概念，在单反的设置参数中有个色彩偏移，
         * 其定义的就是白平衡的色彩偏移值，就是当你去拍一张照片的时候白色是什么颜色的，
         * 在正常情况下白色是（255, 255, 255, 255）但是现实世界中我们是无法找到这样的纯白物体的，
         * 所以在我们用单反拍照之前就会拿一个我们认为是白色的物体让相机记录这个物体的颜色作为白色，
         * 然后拍摄时整张照片的颜色都会依据这个定义的白色来偏移！而这个我们定义的“白色”（比如：255, 253, 251, 247）
         * 和纯白（255, 255, 255, 255）之间的偏移值（0, 2, 4, 8）我们称之为白平衡的色彩偏移。
         * 如果你不理解不要紧，自定义控件系列完结后紧接着就是设计色彩基础
         */
//            ColorMatrix colorMatrix = new ColorMatrix(new float[]{
//                1, 0, 0, 0, 0,
//                0, 1, 0, 0, 0,
//                0, 0, 1, 0, 0,
//                0, 0, 0, 1, 0,
//        });

        // 生成色彩矩阵
        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                0.5F, 0, 0, 0, 0,
                0, 0.5F, 0, 0, 0,
                0, 0, 0.5F, 0, 0,
                0, 0, 0, 1, 0,
        });
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));

    /*
     * 设置描边的粗细，单位：像素px
     * 注意：当setStrokeWidth(0)的时候描边宽度并不为0而是只占一个像素
     */
        mPaint.setStrokeWidth(10);

    }

    /**
     * 初始化资源
     */
    private void initRes(Context context) {
        // 获取位图
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.way);

        /*
         * 计算位图绘制时左上角的坐标使其位于屏幕中心
         * 屏幕坐标x轴向左偏移位图一半的宽度
         * 屏幕坐标y轴向上偏移位图一半的高度
         */
        x = MeasureUtil.getScreenSize((Activity) mContext)[0] / 2 - bitmap.getWidth() / 2;
        y = MeasureUtil.getScreenSize((Activity) mContext)[1] / 2 - bitmap.getHeight() / 2;
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
//        canvas.drawCircle(MeasureUtil.getScreenSize((Activity) mContext)[0] / 2, MeasureUtil.getScreenSize((Activity) mContext)[1] / 2, 200, mPaint);


        // 绘制位图
        canvas.drawBitmap(bitmap, x, y, mPaint);
    }

}
