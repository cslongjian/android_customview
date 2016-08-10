package com.ycf.chenlongjian.ndk_jni_demo;

/**
 * Created by chenlongjian on 2016/8/10.
 */
public class NdkJniUtils {
    static {
        System.loadLibrary("cljJniLibName");
    }

    public native String getCLanguageString();
}
