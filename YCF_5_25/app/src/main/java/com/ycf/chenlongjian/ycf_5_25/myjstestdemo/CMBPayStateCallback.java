package com.ycf.chenlongjian.ycf_5_25.myjstestdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import org.json.JSONObject;

/**
 * Created by chenlongjian on 2016/7/12.
 */
public class CMBPayStateCallback {

    public static final String RESULT_KEY = "pay_status";
    public static final String RESULT_PAYING = "0";
    public static final String RESULT_FAILED = "1";
    public static final String RESULT_SUCCESS = "2";

    private String resultCode = RESULT_PAYING;
    private Handler handler;
     private Context context;

    public CMBPayStateCallback(final Context mycontext)
    {
        Log.w("CMBPayStateCallback","初始化JS回调---");
        this.context = mycontext;
        handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what)
                {

                }
                Toast.makeText(mycontext, msg.obj.toString(),Toast.LENGTH_SHORT).show();
                super.handleMessage(msg);
            }
        };
    }


    @JavascriptInterface
    public void initCmbSignNetPay(final String payData)
    {
        Toast.makeText(context, payData, Toast.LENGTH_LONG).show();
        Log.w("initCmbSignNetPay","初始化JS回调---");
        handler.post(new Runnable() {
            @Override
            public void run() {
                try{
                    JSONObject jsonObject = new JSONObject(payData);
                    String status = jsonObject.getString("sign_status");
                    resultCode = RESULT_PAYING;
                    Message msg = handler.obtainMessage();
                    if (status.equals(RESULT_PAYING))
                    {
                        msg.what = 0;
                        msg.obj = payData;
                    }

                    if (status.equals(RESULT_FAILED))
                    {
                        msg.what = 1;
                        msg.obj = payData;
                    }

                    handler.sendMessage(msg);

                }catch (Exception e)
                {

                }
            }
        });

    }

    @JavascriptInterface
    public void fun1FromAndroid(String name) {
        Toast.makeText(context, name, Toast.LENGTH_LONG).show();
    }



}
