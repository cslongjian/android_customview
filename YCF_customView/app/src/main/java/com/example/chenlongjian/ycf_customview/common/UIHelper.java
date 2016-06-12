package com.example.chenlongjian.ycf_customview.common;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * Created by chenlongjian on 2016/6/12.
 */
public class UIHelper {
    public final static int LISTVIEW_DATA_MORE = 0x01;
    public final static int LISTVIEW_DATA_LOADING = 0x02;
    public final static int LISTVIEW_DATA_FULL = 0x03;
    public final static int LISTVIEW_DATA_EMPTY = 0x04;

//    /**
//     * 创建自定义的ProgressDialog
//     *
//     * @param msg 自定义信息
//     */
//    public static ProgressDialog createProgressDialog(Context context, String msg) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View view = inflater.inflate(R.layout.loading_dialog, null);
//        TextView textView = (TextView) view.findViewById(R.id.progress_dialog_tv);
//        ProgressDialog dialog = new ProgressDialog(context);
//        dialog.show();
//        dialog.setContentView(view);
//        // dialog.setCancelable(false); // 不可以用“返回键”取消
//        if (msg != null && !msg.equals("")) textView.setText(msg);
//
//        return dialog;
//    }

    public static int getStatusBarHeight(Context context) {
        if (context == null)
            return 0;
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        if (context == null)
            return 0;
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        if (context == null)
            return 0;
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     */
    public static int px2sp(Context context, float pxValue) {
        if (context == null)
            return 0;
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     */
    public static int sp2px(Context context, float spValue) {
        if (context == null)
            return 0;
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取屏幕宽度
     */
    public static int getDisplayWidth(Context context) {
        if (context == null)
            return 0;
        WindowManager manage = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return manage.getDefaultDisplay().getWidth();
    }

    /**
     * 获取屏幕高度
     */
    public static int getDisplayHeight(Context context) {
        if (context == null)
            return 0;
        WindowManager manage = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return manage.getDefaultDisplay().getHeight();
    }

    public static void setTextViewTextNoEmpty(TextView textView, CharSequence text) {
        if (textView != null) {
            if (!TextUtils.isEmpty(text)) {
                textView.setText(text);
            }
        }
    }

}
