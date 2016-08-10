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

    //针对后面开源控件的方法
    private String testUrl = "file:///android_asset/TestWebViewJavascriptBridge.html";

//    private String testUrl = "http://61.144.248.29:801/netpayment/BaseHttp.dll?PrePayEUserP?BranchID=0020&CoNo=000089&BillNo=5102071398&Amount=20.00&Date=20160721&ExpireTimeSpan=60&MerchantUrl=http://payapi-dev.yaochufa.com/v2/payment/GMBPayNotify/1/0/2/124&MerchantPara=orderNo=3062027594048&MerchantCode=|VVzjS4SpMRQ5rnMznYbqP0jncJ5ihZGRaOOIrvNJWBKhaSMz0Ah3VGg1WyKJktSV/1ILYiUy7h3i2kwbr9F5IJnr1SeDPfObME2zQXP3I5fU1j7moS81jXLB7rsSi6hz3605h1urIbmGxexmVI3h4Vq9uvrMKvjhkYqurn5/I*OkD/c3ugaM9YUWpM3U368SHc3Q8NEYwGsF/y5xfa30qNx*P4kSCabRNUuRmNQW2jFpb3zgSELLkSlMQBRRgco3ebar/1BkfUQY88FghBlQvLBWFuj*zk4mnJUpz*us2hfmJe6lRP8GIQQ4eQukYlAb8QGJIrjM4zlTpDsoAnf24DO*qcU5/k1DlsmdYDZcgpvZwLsH7X8LAH*kv42PxPeCFgNUvlIhHDU9ndizEpd/wyzVdIvKz70jfsRCmyeFACuihsPtZh6BvaZgUqapw4lrT44Er161fvcF03RKCR8/CQwRCHZuSK*jZKSRVOLPxiXbq2eS|d04888b232cc1b2295ae50489533852c4a3ca27a&MerchantRetUrl=http://yaochufa/payresult/&MerchantRetPara=orderNo=3062027594048";
    //针对原生方式的使用案例
//    private String testUrl = "file:///android_asset/demo.html";

//    两者只有一种可以使用。用了其中一种，另外一种将会失效


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
//        webView.addJavascriptInterface(cmbPayStateCallback,"WebViewJavascriptBridge");

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

        this.webView.registerHandler("initCmbSignNetPay", new BridgeHandler()
        {
            public void handler(String paramAnonymousString, CallBackFunction paramAnonymousCallBackFunction)
            {
                Log.i("initCmbSignNetPay-----", "回传结果：---------：" + paramAnonymousString);
                Toast.makeText(WebViewJsTestActvity.this, "回传结果：---------:" + paramAnonymousString, Toast.LENGTH_SHORT).show();
                if (paramAnonymousCallBackFunction != null)
                    paramAnonymousCallBackFunction.onCallBack("测试");
            }
        });




    }
}
