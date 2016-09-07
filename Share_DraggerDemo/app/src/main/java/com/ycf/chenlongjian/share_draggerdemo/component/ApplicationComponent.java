package com.ycf.chenlongjian.share_draggerdemo.component;

import com.google.gson.Gson;
import com.ycf.chenlongjian.share_draggerdemo.module.ApplicationModule;
import com.ycf.chenlongjian.share_draggerdemo.module.ContainModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by chenlongjian on 2016/9/6.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Gson getGson();// 暴露Gson对象接口


    ContainComponent plus(ContainModule module);//添加声明

}

