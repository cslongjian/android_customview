package com.ycf.chenlongjian.share_draggerdemo;

import javax.inject.Inject;

/**
 * Created by chenlongjian on 2016/9/5.
 */
public class Cat {
    private String mName;

    // 用Inject标记构造函数,表示用它来注入到目标对象中去
    @Inject
    public Cat() {
        mName = "无参数的大肥猫";
    }

    public Cat(String name){
        mName = name;
    }

    public String getmName() {
        return mName;
    }

}
