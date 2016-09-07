package com.ycf.chenlongjian.share_draggerdemo.component;


import com.ycf.chenlongjian.share_draggerdemo.ContainActivity;
import com.ycf.chenlongjian.share_draggerdemo.module.ContainModule;
import com.ycf.chenlongjian.share_draggerdemo.scope.ContainScope;
import dagger.Subcomponent;

/**
 * Created by chenlongjian on 2016/9/6.
 */
@ContainScope
@Subcomponent(modules = ContainModule.class)
public interface ContainComponent {
    void inject(ContainActivity activity);
}


