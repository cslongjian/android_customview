package com.ycf.chenlongjian.ycf_hotfix;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by chenlongjian on 2016/10/13.
 */
public class Function {

    //获取版本号
    public static int getVersionCode(Context context){
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        }catch(PackageManager.NameNotFoundException e){
            return 0;
        }
    }
}
