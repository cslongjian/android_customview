package com.ycf.chenlongjian.ycf_5_40;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by chenlongjian on 2016/10/8.
 */
public class CompenentBtn extends LinearLayout{

    public static final int TYPE_NORMAL = 0;//普通预订
    public static final int TYPE_MIANYUYUE = 1;//免预约
    public static final int TYPE_PRESALE = 2;//普通预售
    public static final int TYPE_GROUPPRESALE = 3;//团购预售
    public static final int TYPE_GROUPPRESALE_ENABLE = 4;//团购预售 不可点击状态

    public int current_type = 0;

    Button btn;
    Button mianyuyue;

    public CompenentBtn(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        LayoutInflater.from(context).inflate(R.layout.compenentbtn, this);
        btn = (Button) findViewById(R.id.btn);
        mianyuyue = (Button) findViewById(R.id.mianyuyuebtns);
        setOnTouchListener(onTouch);
    }

    public void setType(int type)
    {
        current_type = type;
        switch (type)
        {
            case TYPE_NORMAL:
                break;
            case TYPE_MIANYUYUE:
                mianyuyue.setVisibility(View.VISIBLE);
                break;
            case TYPE_PRESALE:
                btn.setBackground(getContext().getResources().getDrawable(R.drawable.detail_list_btn_presale_bg));
//                btn.setTextColor(getContext().getResources().getColor(R.color.cg_50));
                break;
            case TYPE_GROUPPRESALE:
//                btn.setBackground(R.drawable.detail_list_description_btn_top_bg_presale);
                btn.setBackgroundResource(R.drawable.detail_list_description_btn_top_bg_presale);
                mianyuyue.setVisibility(View.VISIBLE);
                mianyuyue.setBackgroundResource(R.drawable.detail_list_description_btn_bottom_bg_presale);
                mianyuyue.setTextColor(getContext().getResources().getColor(R.color.cg_50));
                break;
            case TYPE_GROUPPRESALE_ENABLE:
                setOnClickListener(null);
                btn.setBackgroundResource(R.drawable.detail_list_description_btn_top_bg_presale);
                mianyuyue.setVisibility(View.VISIBLE);
                mianyuyue.setBackgroundResource(R.drawable.detail_list_description_btn_bottom_bg_presale);
                mianyuyue.setTextColor(getContext().getResources().getColor(R.color.cg_50));
                break;
            default:
                break;

        }

    }





    private View.OnTouchListener onTouch  =  new View.OnTouchListener()
    {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            int action =motionEvent.getAction();

            if(action==MotionEvent.ACTION_DOWN){

//                Log.w("MotionEvent","ACTION_DOWN");
                switch (current_type)
                {
                    case TYPE_NORMAL:
                        btn.setBackground(getContext().getResources().getDrawable(R.drawable.detail_list_btn_presale_bg));
                        break;
                    case TYPE_MIANYUYUE:
                        break;
                    case TYPE_PRESALE:
                        break;
                    case TYPE_GROUPPRESALE:
                        btn.setBackgroundResource(R.drawable.detail_list_description_btn_top_bg_press_presale);
                        mianyuyue.setBackgroundResource(R.drawable.detail_list_description_btn_bottom_bg_press_presale);
                        break;
                    default:
                        break;
                }
            }

            if(action==MotionEvent.ACTION_MOVE){

                switch (current_type)
                {
                    case TYPE_NORMAL:
                        btn.setBackground(getContext().getResources().getDrawable(R.drawable.detail_list_btn_presale_bg));
                        break;
                    case TYPE_MIANYUYUE:
                        break;
                    case TYPE_PRESALE:
                        break;
                    case TYPE_GROUPPRESALE:
                        btn.setBackgroundResource(R.drawable.detail_list_description_btn_top_bg_press_presale);
                        mianyuyue.setBackgroundResource(R.drawable.detail_list_description_btn_bottom_bg_press_presale);
                        break;
                    default:
                        break;
                }
            }

            if(action==MotionEvent.ACTION_UP){
                switch (current_type)
                {
                    case TYPE_NORMAL:
                        btn.setBackground(getContext().getResources().getDrawable(R.drawable.detail_list_btn_bg));
                        break;
                    case TYPE_MIANYUYUE:
                        break;
                    case TYPE_PRESALE:
                        break;
                    case TYPE_GROUPPRESALE:
                        btn.setBackgroundResource(R.drawable.detail_list_description_btn_top_bg_presale);
                        mianyuyue.setBackgroundResource(R.drawable.detail_list_description_btn_bottom_bg_presale);
                        break;
                    default:
                        break;
                }
            }



            return false;
        }
    };


}
