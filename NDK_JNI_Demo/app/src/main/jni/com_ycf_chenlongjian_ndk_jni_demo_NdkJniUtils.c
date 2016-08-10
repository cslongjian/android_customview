//
// Created by chenlongjian on 2016/8/10.
//

#include "com_ycf_chenlongjian_ndk_jni_demo_NdkJniUtils.h"



JNIEXPORT jstring JNICALL Java_com_ycf_chenlongjian_ndk_1jni_1demo_NdkJniUtils_getCLanguageString
        (JNIEnv *env, jobject obj) {

    return (*env)->NewStringUTF(env, "This is Jni test!!!");

//    return env->NewStringUTF((char *)"Hello from JNI !");

}