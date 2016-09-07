package com.ycf.chenlongjian.share_draggerdemo.module;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chenlongjian on 2016/9/6.
 */

@Module
public class ApplicationModule {

    /**
     * @Provides 注解表示这个方法是用来创建某个实例对象的，这里我们创建返回Gson对象
     * 方法名随便，一般用provideXXX结构
     * @return 返回注入对象
     */
    @Singleton
    @Provides
    public Gson provideGson(){
        return new Gson();
    }
}
