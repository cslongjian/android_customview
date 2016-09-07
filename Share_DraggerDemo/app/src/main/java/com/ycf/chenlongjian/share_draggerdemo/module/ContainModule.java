package com.ycf.chenlongjian.share_draggerdemo.module;

import com.ycf.chenlongjian.share_draggerdemo.Cat;
import com.ycf.chenlongjian.share_draggerdemo.scope.ContainScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chenlongjian on 2016/9/6.
 */

@Module
public class ContainModule {

    @ContainScope
    @Provides
    public Cat getCat(){
        return new Cat("--包含里的大肥猫--");
    }

}
