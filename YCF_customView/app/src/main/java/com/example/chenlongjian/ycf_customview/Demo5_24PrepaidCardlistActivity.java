package com.example.chenlongjian.ycf_customview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chenlongjian.ycf_customview.model.PrepaidCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenlongjian on 16/6/15.
 */
public class Demo5_24PrepaidCardlistActivity extends Activity {

    private TextView mCancel;
    private ListView mListview;
    private List<PrepaidCard> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_5_24prepaidcardlist);

        initdata();
        initView();

    }

    public void initdata(){
        list = new ArrayList<PrepaidCard>();
        for (int i = 0;i<5;i++)
        {
            PrepaidCard prepaidCard = new PrepaidCard();
            prepaidCard.setName("测试数据"+i);
            list.add(prepaidCard);
        }
    }

    public void initView(){

        mCancel = (TextView) findViewById(R.id.cancle);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mListview = (ListView) findViewById(R.id.listview);
        mListview.setAdapter(null);
    }

    @Override
    public void finish() {
        // TODO Auto-generated method stub
        super.finish();
        //关闭窗体动画显示
        this.overridePendingTransition(R.anim.push_bottom_out,0);
    }

    private class CustomAdapter extends BaseAdapter {
        private List<PrepaidCard> sorts;
        private Context mContext;
        private boolean ifAround;

        //	private OnClickDelegator mDelegator;
        public CustomAdapter(Context ctx, List<PrepaidCard> sorts, boolean ifAround) {
            this.mContext = ctx;
            this.sorts = sorts;
            this.ifAround = ifAround;
        }

        @Override
        public int getCount() {
            return sorts.size();
        }

        @Override
        public Object getItem(int position) {
            return sorts.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
//                convertView = new CheckableItem(mContext, ifAround);
            }
            PrepaidCard prepaidCard = sorts.get(position);
//            ((CheckableItem) convertView).setName(sort.getName());
//		((CheckableItem)convertView).setChecked(sort.isCheck());
            return convertView;
        }
    }



}
