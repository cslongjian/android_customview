package com.ycf.chenlongjian.ycf_testdemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by chenlongjian on 2016/7/4.
 */
public class WebViewTestActivity extends Activity {

    private WebView mWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_webview);

        mWebview = (WebView) findViewById(R.id.webview);

        initView();

    }

    void initView()
    {
        WebSettings webSettings = mWebview.getSettings();
        // 设置User-Agent
        webSettings.setUserAgentString(
                webSettings.getUserAgentString() + " Yaochufa/Android AppVersion/" + 1.0);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setBuiltInZoomControls(true);
        if (android.os.Build.VERSION.SDK_INT >= 11)
            webSettings.setDisplayZoomControls(false);

		/*------------------定位相关------------------------------*/

        mWebview.addJavascriptInterface(new JavaLocalObject(), "local");

        webSettings.setSupportZoom(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setAppCacheMaxSize(6 * 1024 * 1024);
        webSettings.setAllowFileAccess(true);
        webSettings.setAppCacheEnabled(false);

//         setAppCacheEnabled设为true时，需指定缓存路径setAppCachePath才能生效，整个应用只需要设置一次
        String appCachePath = getCacheDir() + "/webViewCache";
        webSettings.setAppCachePath(appCachePath);


//        if (Build.VERSION.SDK_INT >= 19) {
//            mWebview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        }

        mWebview.setWebViewClient(mWebViewClient);
//
        String url = "http://payapi-test.yaochufa.com/v2/Payment/Pay?system=m&version=V_3.0&orderNo=1PCT5571002711580&payTypeId=112&PaySite=4&version=58&appcitycode=440100&system=android&DeviceToken=ffffffff-8b19-457d-c9c9-bdbd0033c587&SecurityKey=X1EHN022IM3FjlGZ1PFNv1N1D2jGCmEm2jeX7sXFBsj4JkCJ6gRSI%2FCQzLhcPb887j3b0zMuciHNw0jxi8i3O2fPyvTxWy7eVqd874%2BdDttlYsqVzKCWSzY0Mcl94Bx4zddvXaWC%2BBslorCal25DU0pNN4HMbvI9&userId=237399";

        mWebview.loadUrl(url);

    }

    final class JavaLocalObject {
        @JavascriptInterface
        public void getValue(final String value) {
            if (null == value)
                return;
            runOnUiThread(new Runnable() {
                public void run() {
                }
            });
        }

        @JavascriptInterface
        public void showHtml(final String value) {
        }

    }


    //页面加载监听
    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onPageFinished(final WebView view, String url) {

            super.onPageFinished(view, url);
            view.loadUrl(
                    "javascript:window.local.getValue(document.getElementById('appShare').value)");
            if (!TextUtils.isEmpty(view.getTitle())) {
                if (!"找不到网页".equals(view.getTitle())) {

                }
            }

        }





        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
//            if (!NetworkUtils.isNetworkConnected(getApplicationContext())&&!isFirstLoad){
//                showNetBadToast();
//            }else {
            super.onPageStarted(view, url, favicon);

        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description,
                                    String failingUrl) {
//            if (!NetworkUtils.isNetworkConnected(getApplicationContext())&&!isFirstLoad) {
//                showNetBadToast();
//            }else {
            mWebview.stopLoading();
            mWebview.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);

//            }
        }
    };


}
