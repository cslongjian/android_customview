package com.ycf.chenlongjian.ycf_5_40;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CompenentBtn normal;
    private CompenentBtn mianyuyue;
    private CompenentBtn presale;
    private CompenentBtn grouppresale;
    private EditText textView;
    private Button btn;

    private String html;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        normal = (CompenentBtn) findViewById(R.id.normal);
        normal.setType(CompenentBtn.TYPE_NORMAL);

        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.w("onClick","onClick");
            }
        });

        mianyuyue = (CompenentBtn) findViewById(R.id.mianyuyue);
        mianyuyue.setType(CompenentBtn.TYPE_MIANYUYUE);
        mianyuyue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.w("onClick","onClick");
            }
        });

        presale = (CompenentBtn) findViewById(R.id.presale);
        presale.setType(CompenentBtn.TYPE_PRESALE);
        presale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.w("onClick","onClick");
            }
        });

        grouppresale = (CompenentBtn) findViewById(R.id.grouppresale);
        grouppresale.setType(CompenentBtn.TYPE_GROUPPRESALE);
        grouppresale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.w("onClick","onClick  ");
            }
        });

        textView = (EditText) findViewById(R.id.edittext);

        btn = (Button) findViewById(R.id.btndiolog);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                html = textView.getText().toString();
                HDPreSaleDescriptDialog dialog = new HDPreSaleDescriptDialog(MainActivity.this, html);
                dialog.show();
            }
        });






    }
}
