
# android NDK使用案例
* #环境配置

	由于Android studio 从1.3Beta 开始支持NDK的使用。所以不需要像以前那样在window环境下去配置模拟出Linux环境安装cygwin了。
	
	* 在SDK tool 中NDK 选中。自动进行下载
	* 在project Structure SDK Location 中 Android NDK Location 可以设置本地。或者进行网络下载设置
	
	

*  #实现

	1在普通的包内生产要调用SO库相关方法。

	2通过用.CLASS文件   命令 Javah -jni 文件 生成对应的.C文件

	3通过.C文件 去实现 声明的接口  

	在实现上。需要学会c 和 JNI 语法的使用 

	
* #配置
	build.gradle 中添加 ndk模块 生成对应的so库

* #踩的坑
	* clean 时候无法删除build内的一个文件夹。（使用360强删）
	* 在C文件中使用了C++的写法  （使用会Ｃ写法）

* #总结
	这种坑还是得自己踩一遍，才知道如何配置和设置的。