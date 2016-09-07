package com.ycf.chenlongjian.share_draggerdemo;

import android.app.Application;
import android.util.Log;

import com.ycf.chenlongjian.share_draggerdemo.component.ApplicationComponent;
import com.ycf.chenlongjian.share_draggerdemo.component.ContainComponent;
import com.ycf.chenlongjian.share_draggerdemo.component.DaggerApplicationComponent;
import com.ycf.chenlongjian.share_draggerdemo.module.ContainModule;

/**
 * Created by chenlongjian on 2016/9/6.
 */
public class MainApplication extends Application {

    private ApplicationComponent mApplicationComponent;
    private static MainApplication sApplication;

    private ContainComponent mContainComponent;

    public static MainApplication getInstance() {
        return sApplication;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
        //初始化公用组件部分
        mApplicationComponent = DaggerApplicationComponent.builder().build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public ContainComponent getContainComponent() {
        if (mContainComponent == null){
            Log.w("ContainComponent","getmContainComponent----------------------");
            mContainComponent = mApplicationComponent.plus(new ContainModule());
        }
        return mContainComponent;
    }
}
