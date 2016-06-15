package com.example.chenlongjian.ycf_customview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.chenlongjian.ycf_customview.widget.DateChooseLayout;

public class MainActivity extends AppCompatActivity implements DateChooseLayout.OnItemChooseListener {

    private DateChooseLayout dateChooseLayout;

    private Button mBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        dateChooseLayout = (DateChooseLayout)findViewById(R.id.choose_date);
//        dateChooseLayout.setOnItemChooseListener(this);
//        dateChooseLayout.setSaveChooseFlag(true);

        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),DialogActivity.class);
                startActivity(intent);
            }
        });

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
