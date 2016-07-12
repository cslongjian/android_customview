package com.ycf.chenlongjian.ycf_5_25.myjstestdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.widget.Toast;

import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;

import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.github.lzyzsd.jsbridge.DefaultHandler;
import com.ycf.chenlongjian.ycf_5_25.R;

/**
 * Created by chenlongjian on 2016/7/12.
 */
public class WebViewJsTestActvity extends Activity {

    private BridgeWebView webView;

    private CMBPayStateCallback cmbPayStateCallback;

//    private String testUrl = "file:///android_asset/TestWebViewJavascriptBridge.html";

    private String testUrl = "file:///android_asset/demo.html";


//    private String testUrl = "file:///android_asset/test.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webviewjstest);

        webView = (BridgeWebView)findViewById(R.id.webview);

        // 对WebView进行设置
        WebSettings set = webView.getSettings();
        // 支持JS
        set.setJavaScriptEnabled(true);
        set.setSaveFormData(false);
        set.setSavePassword(false);
        set.setSupportZoom(false);
        webView.setDefaultHandler(new DefaultHandler());


        cmbPayStateCallback = new CMBPayStateCallback(this);
        webView.addJavascriptInterface(cmbPayStateCallback,"WebViewJavascriptBridge");

        webView.loadUrl(testUrl);

//        this.webView.registerHandler("submitFromWeb", new BridgeHandler()
//        {
//            public void handler(String paramAnonymousString, CallBackFunction paramAnonymousCallBackFunction)
//            {
//                Log.i("hrq-----", "handler = submitFromWeb, data from web = " + paramAnonymousString);
//                Toast.makeText(WebViewJsTestActvity.this, "回传结果：" + paramAnonymousString, Toast.LENGTH_SHORT).show();
//                if (paramAnonymousCallBackFunction != null)
//                    paramAnonymousCallBackFunction.onCallBack("测试");
//            }
//        });
//        this.webView.registerHandler("initCmbSignNetPay", new BridgeHandler()
//        {
//            public void handler(String paramAnonymousString, CallBackFunction paramAnonymousCallBackFunction)
//            {
//                Log.i("hrq-----", "回传结果：" + paramAnonymousString);
//                Toast.makeText(WebViewJsTestActvity.this, "回传结果：" + paramAnonymousString, Toast.LENGTH_SHORT).show();
//                if (paramAnonymousCallBackFunction != null)
//                    paramAnonymousCallBackFunction.onCallBack("测试");
//            }
//        });




    }
}
