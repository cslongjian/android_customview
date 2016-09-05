
# android 浏览器调起APP 调查
* #调查原因

	有部分浏览器在调起应用时候，无法成功，出发空白页。
	
	

*  #调起处理方式和设置

	##在HTML页面中
	
	 配置好如下格式的跳转链接 

	<\a href="[scheme]://[host]/[path]?[query]"> 启动应用程序<\/a> 
	
		各个项目含义如下所示：

		scheme：判别启动的App。 (必须添加项）

		host：适当记述

		path：传值时必须的key     ※没有也可以

		query：获取值的Key和Value  ※没有也可以


	##Android端
	
	配置中的activity 必须添加过滤<intent-filer>	

		<intent-filter>  
    	<action android:name="android.intent.action.VIEW"/>  
    	<category android:name="android.intent.category.DEFAULT" />  
    	<category android:name="android.intent.category.BROWSABLE" />  
    	<data android:scheme="test" android:host="jp.app" android:pathPrefix="/openwith"/>  
		</intent-filter>

	其中必须的内容仅scheme，没有其他内容app也能启动。

	##注意的点
	intent-filter的内容【android.intent.action.MAIN】和 【android.intent.category.LAUNCHER】这2个，不能与这次追加的内容混合。
                 所以，如果加入了同一个Activity，请按以下这样做，否则会导致应用图标在桌面消失等问题。

		<intent-filter>  
    	<action android:name="android.intent.action.MAIN"/>  
    	<category android:name="android.intent.category.LAUNCHER" />  
		</intent-filter>  
		<intent-filter>  
    	<action android:name="android.intent.action.VIEW"/>  
    	<category android:name="android.intent.category.DEFAULT" />  
    	<category android:name="android.intent.category.BROWSABLE" />  
    	<data android:scheme="myapp" android:host="jp.app" android:pathPrefix="/openwith"/>  
		</intent-filter> 
 	这样就没有问题
	
* #测试的浏览器类型 （小米3设备）
	
		浏览器类型        	 跳转情况
		系统自带浏览器     	正常跳转
		火狐浏览器		    正常跳转
		欧朋浏览器			正常跳转	
		360浏览器			正常跳转（有弹出框，请求跳的提示）
		百度浏览器			垃圾啊。自己就挂了。还没启动成功。。	
		搜狐浏览器			无法启动浏览器
		QQ浏览器				正常跳转	（有弹出框，请求跳的提示）
		猎豹浏览器			正常跳转
		UC浏览器				正常跳转，（有做拦截，选择框（权限记录，拒绝还是单次许可））




* #结论
	市面上大部分第三方浏览器都已经支持这种跳转。暂无发现那种浏览器不支持 scheme 跳转

