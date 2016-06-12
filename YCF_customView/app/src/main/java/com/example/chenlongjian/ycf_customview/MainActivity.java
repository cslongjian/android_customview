package com.example.chenlongjian.ycf_customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.chenlongjian.ycf_customview.widget.DateChooseLayout;

public class MainActivity extends AppCompatActivity implements DateChooseLayout.OnItemChooseListener {

    private DateChooseLayout dateChooseLayout;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dateChooseLayout = (DateChooseLayout)findViewById(R.id.choose_date);
        dateChooseLayout.setOnItemChooseListener(this);
        dateChooseLayout.setSaveChooseFlag(true);
    }

    @Override
    public void onItemChoose(String date) {

    }

    @Override
    public void chooseAction() {

    }

    @Override
    public void onHideMask() {

    }

    @Override
    public void onShowMask() {

    }
}
