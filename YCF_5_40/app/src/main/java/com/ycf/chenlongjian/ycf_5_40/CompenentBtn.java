package com.ycf.chenlongjian.ycf_5_40;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by chenlongjian on 2016/10/8.
 */
public class CompenentBtn extends LinearLayout{

    public static final int TYPE_NORMAL = 0;//普通预订
    public static final int TYPE_MIANYUYUE = 1;//免预约
    public static final int TYPE_PRESALE = 2;//普通预售
    public static final int TYPE_GROUPPRESALE = 3;//团购预售

    public int current_type = 0;


    Button btn;
    Button mianyuyue;

    public CompenentBtn(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        LayoutInflater.from(context).inflate(R.layout.compenentbtn, this);
        btn = (Button) findViewById(R.id.btn);
        mianyuyue = (Button) findViewById(R.id.mianyuyue);
//        titleText = (TextView) findViewById(R.id.title_text);
//        leftButton = (Button) findViewById(R.id.button_left);
//        leftButton.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((Activity) getContext()).finish();
//            }
//        });
        setOnTouchListener(onTouch);
    }

    public void SetType(int type)
    {
        current_type = type;
        switch (type)
        {
            case TYPE_NORMAL:

                break;
            case TYPE_MIANYUYUE:
                break;
            case TYPE_PRESALE:
                break;
            case TYPE_GROUPPRESALE:
                break;
            default:
                break;

        }

    }





    private View.OnTouchListener onTouch  =  new View.OnTouchListener()
    {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (current_type)
            {
                case TYPE_NORMAL:

                    break;
                case TYPE_MIANYUYUE:
                    break;
                case TYPE_PRESALE:
                    break;
                case TYPE_GROUPPRESALE:
                    break;
                default:
                    break;

            }
            return false;
        }
    };


}
