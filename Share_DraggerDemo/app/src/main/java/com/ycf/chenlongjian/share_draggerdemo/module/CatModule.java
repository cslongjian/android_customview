package com.ycf.chenlongjian.share_draggerdemo.module;

import com.ycf.chenlongjian.share_draggerdemo.Cat;
import com.ycf.chenlongjian.share_draggerdemo.scope.CatScope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chenlongjian on 2016/9/5.
 */

@Module
public class CatModule {

    // 这个方法需要一个String参数，在Dagger2注入中，这些参数也是注入形式的，也就是
    // 要有其他对方提供参数name的生成，不然会造成编译出错
    @CatScope
    @Provides
    public Cat provideCat(String catname){
        return new Cat(catname);
    }

    // 这里提供了一个生成String的方法，在这个Module里生成Cat实例时，会查找到这里
    // 可以为上面提供String类型的参数

    @Provides
    public String provideCatname(){
        return "我是Module中来的肥猫``";
    }
}
