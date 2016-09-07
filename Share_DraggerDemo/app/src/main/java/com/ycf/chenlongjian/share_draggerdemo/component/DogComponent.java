package com.ycf.chenlongjian.share_draggerdemo.component;

import android.app.Activity;

import dagger.Component;

/**
 * Created by chenlongjian on 2016/9/6.
 */

@Component
public interface DogComponent {

    void inject(Activity activity);
}
