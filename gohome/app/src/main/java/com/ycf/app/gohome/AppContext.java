package com.ycf.app.gohome;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by chenlongjian on 2017/1/25.
 */

public class AppContext extends Application {

    private static AppContext sAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sAppContext = this;
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }

    public static AppContext getAppContext() {
        return sAppContext;
    }
}
