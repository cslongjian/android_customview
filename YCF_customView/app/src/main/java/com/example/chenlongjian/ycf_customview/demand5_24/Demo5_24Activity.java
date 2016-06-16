package com.example.chenlongjian.ycf_customview.demand5_24;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chenlongjian.ycf_customview.R;
import com.example.chenlongjian.ycf_customview.model.PrepaidCard;

/**
 * Created by chenlongjian on 16/6/15.
 */
public class Demo5_24Activity extends Activity {

    private static final int REQUEST = 1;

    private Button mBtn;
    private Button mBtn2;
    private TextView title1;
    private TextView title2;
    private TextView title3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_demo5_24);

        title1 = (TextView) findViewById(R.id.title);
        title2 = (TextView) findViewById(R.id.title2);
        title3 = (TextView) findViewById(R.id.title3);

        mBtn = (Button) findViewById(R.id.btn);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getBaseContext(),Demo5_24PrepaidCardlistActivity.class);
//                startActivity(intent);
                startActivityForResult(intent,REQUEST);

            }
        });

        mBtn2 = (Button) findViewById(R.id.btn2);

        mBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getBaseContext(),Demo5_24PasswordActivity.class);
//                startActivity(intent);
                startActivityForResult(intent,REQUEST);

            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==REQUEST && resultCode == Activity.RESULT_OK)
        {

            Bundle bun = data.getExtras();
            final PrepaidCard prepaidCard = bun.getParcelable("prepaidCard");

            new Thread() {
                public void run() {
                    //这儿是耗时操作，完成之后更新UI；
                    runOnUiThread(new Runnable(){

                        @Override
                        public void run() {
                            //更新UI
                            title1.setText(prepaidCard.getName());
                            title2.setText(prepaidCard.getSort()+"");
                            title3.setText(prepaidCard.getShortName()+"");
                        }

                    });
                }
            }.start();
//            title1.post(new Runnable(){
//                @Override
//                public void run() {
//                    title1.setText(prepaidCard.getName());
//                }
//            });
//            title2.post(new Runnable(){
//                @Override
//                public void run() {
//                    title2.setText(prepaidCard.getSort()+"");
//                }
//
//            });
//            title3.post(new Runnable(){
//                @Override
//                public void run() {
//                    title3.setText(prepaidCard.getShortName()+"");
//                }
//
//            });


            Log.w("onActivityResult",prepaidCard.getName());
            Log.w("onActivityResult",prepaidCard.getSort());
            Log.w("onActivityResult",prepaidCard.getShortName());

        }
    }
}
