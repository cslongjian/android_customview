package com.ycf.chenlongjian.share_draggerdemo.component;


import com.ycf.chenlongjian.share_draggerdemo.MainActivity;
import com.ycf.chenlongjian.share_draggerdemo.MainApplication;
import com.ycf.chenlongjian.share_draggerdemo.SecondActivity;
import com.ycf.chenlongjian.share_draggerdemo.module.CatModule;
import com.ycf.chenlongjian.share_draggerdemo.module.MainModule;
import com.ycf.chenlongjian.share_draggerdemo.scope.CatScope;

import dagger.Component;

/**
 * Created by chenlongjian on 2016/9/5.
 */

//用@Component表示这个接口是一个连接器，能用@Component注解的只能是interface或者抽象类
//这里表示Component会从MainModule类中拿那些用@Provides注解的方法来生成需要注入的实例
@CatScope
@Component(dependencies=ApplicationComponent.class, modules={MainModule.class, CatModule.class})
public abstract class MainComponent {
    /**
     * 需要用到这个连接器的对象，就是这个对象里面有需要注入的属性
     * （被标记为@Inject的属性）
     * 这里inject表示注入的意思，这个方法名可以随意更改，但建议就
     * 用inject即可。
     */
    public abstract void inject(MainActivity activity);

    public abstract void inject(SecondActivity activity);

    private static MainComponent sComponent;
    public static MainComponent getInstance(){
        if (sComponent == null){
//            sComponent = DaggerMainComponent.builder().build();
            sComponent = DaggerMainComponent.builder()
                    .applicationComponent(MainApplication.getInstance()
                            .getApplicationComponent())
                    .build();
        }

        return sComponent;
    }

}
