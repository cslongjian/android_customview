package com.chenlong.activitys;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.chenlong.activity.navigationview.R;
import com.chenlong.customview.NavigationView;

public class MainActivity extends Activity {

    private Context context;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        context = this;
        initView();
    }

    private void initView()
    {
        navigationView = (NavigationView) super.findViewById(R.id.nav_main);
        navigationView.setTitle("自定义导航头");
        navigationView.setClickCallBack(new NavigationView.ClickCallBack() {
            @Override
            public void onBackClick() {
                Log.w("MainActivity","--自定义导航头左边按钮点击");
                Toast.makeText(context,"左边------",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightClick() {
                Log.w("MainActivity","--自定义导航头右边按钮点击");
                Toast.makeText(context,"--自定义导航头右边按钮点击",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
