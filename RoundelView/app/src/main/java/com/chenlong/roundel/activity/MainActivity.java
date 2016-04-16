package com.chenlong.roundel.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.chenlong.roundel.ui.RoundelMenuLayout;

public class MainActivity extends Activity {

    private String[] mItemTexts = new String[] { "安全中心 ", "特色服务", "投资理财",
            "转账汇款", "我的账户", "信用卡" };
    private int[] mItemImgs = new int[] { R.drawable.fang1,
            R.drawable.fang2, R.drawable.fang3,
            R.drawable.fang4, R.drawable.fang5,
            R.drawable.fang6 };

    private RoundelMenuLayout roundelMenuLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        setTitle("转盘View Demo");

        roundelMenuLayout = (RoundelMenuLayout) findViewById(R.id.id_menulayout);
        roundelMenuLayout.setMenuItemIconsAndTexts(mItemImgs,mItemTexts);

        roundelMenuLayout.setOnMenuItemClickListener(new RoundelMenuLayout.OnMenuItemClickListener() {
            @Override
            public void itemClick(View view, int pos) {
                Toast.makeText(MainActivity.this,mItemTexts[pos],Toast.LENGTH_SHORT).show();
            }

            @Override
            public void itemCenterClick(View view) {
                Toast.makeText(MainActivity.this,
                        "you can do something just like ccb  ",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
