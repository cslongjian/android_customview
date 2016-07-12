package com.ycf.chenlongjian.ycf_5_25;


import cmb.pb.util.CMBKeyboardFunc;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by chenlongjian on 2016/7/12.
 */
public class KeyboardTestActivity extends Activity {

    EditText m_editURL = null;

    static private WebView webview;

    private String testUrl = "file:///android_asset/2.html";
    private String DevUrl = "http://99.6.150.83/mobilehtml/netpaytest.html";
    private String STUrl = "http://99.12.74.148/netpayment/mbpay/MBPayTest_Dev.html";

    @SuppressWarnings("deprecation")
    public void init(){
        webview = (WebView) findViewById(R.id.webview);
        m_editURL = (EditText) findViewById(R.id.editText1);

        m_editURL.setText(testUrl);

        Button button1 = (Button)findViewById(R.id.button1);

        if(button1 != null)
        {
            button1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    // TODO Auto-generated method stub
                    LoadUrl();
                }
            });
        }


        // 对WebView进行设置
        WebSettings set = webview.getSettings();
        // 支持JS
        set.setJavaScriptEnabled(true);
        set.setSaveFormData(false);
        set.setSavePassword(false);
        set.setSupportZoom(false);



        //webview.loadUrl(DevUrl);
        LoadUrl();
        webview.setWebViewClient( new  WebViewClient() {
            public   boolean   shouldOverrideUrlLoading(WebView view, String url){
                // 使用当前的WebView加载页面
                CMBKeyboardFunc kbFunc = new CMBKeyboardFunc(KeyboardTestActivity.this);
                if(kbFunc.HandleUrlCall(webview, url) == false)
                {
                    return super.shouldOverrideUrlLoading(view, url);
                }
                else
                {
                    return true;
                }
                //view.loadUrl(url);
                //return  true ;
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboardtest);
        init();
    }

    private void LoadUrl()
    {
        try
        {

            CookieSyncManager.createInstance(KeyboardTestActivity.this.getApplicationContext());
            CookieManager.getInstance().removeAllCookie();
            CookieSyncManager.getInstance().sync();
        }
        catch(Exception e)
        {

        }
        webview.loadUrl(m_editURL.getText().toString());
    }
}