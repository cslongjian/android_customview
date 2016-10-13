package com.ycf.chenlongjian.ycf_5_40;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

/**
 * Created by chenlongjian on 2016/10/9.
 */
public class HDPreSaleDescriptDialog extends Dialog implements View.OnClickListener {

    private static final String STYLE_CSS_START_DESC = "<head><style>body{font-size:0.9em;color:#ffffff}ul{padding-left:1.1em} ul li{text-decoration: none;list-style-type:disc; padding:0; margin:0;}img{max-width: 90%;text-align: center;}</style></head>";
    private static final String STYLE_CSS_END = "</body>";

    private Context mContext;
    private LayoutInflater mInflater;
    private String htmltext;


    private WebView mWebView;
    private Button mCloseBtn;

    public HDPreSaleDescriptDialog(Context context, String html) {
        super(context, R.style.dialog_style);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        htmltext = html;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.package_presaleinfo_dialog);
        onInitView();
    }

    private void onInitView() {
//        setCanceledOnTouchOutside(true);
//        setCancelable(true);

        mWebView = (WebView) findViewById(R.id.wv_desc);
        mWebView.setBackgroundColor(2);
        mCloseBtn = (Button) findViewById(R.id.btn_close);


        mCloseBtn.setOnClickListener(this);

        StringBuilder html = new StringBuilder();
        html.append(htmltext);

        if (!TextUtils.isEmpty(html)) {
            mWebView.loadDataWithBaseURL(null, STYLE_CSS_START_DESC + html
                    + STYLE_CSS_END, "text/html", "UTF-8", null);
        }


    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_close) {
            dismiss();
        }

    }
}