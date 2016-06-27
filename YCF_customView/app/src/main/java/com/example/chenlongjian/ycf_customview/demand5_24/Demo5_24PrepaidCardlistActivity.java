package com.example.chenlongjian.ycf_customview.demand5_24;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chenlongjian.ycf_customview.R;
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
            prepaidCard.setSort("排序名"+i);
            prepaidCard.setShortName("￥"+i*50+100);
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
        CustomAdapter adapter = new CustomAdapter(this,list,false);
        mListview.setAdapter(adapter);

        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PrepaidCard prepaidCard = list.get(position);
                Bundle bundle = new Bundle();
                bundle.putParcelable("prepaidCard", prepaidCard);
                Intent intent = new Intent();
                intent.putExtras(bundle);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        }
        );
    }


    @Override
    public void finish() {
        // TODO Auto-generated method stub
        super.finish();
        //关闭窗体动画显示
        this.overridePendingTransition(R.anim.push_bottom_out,0);
    }

    private class CustomAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        private List<PrepaidCard> sorts;
        private Context mContext;
        private boolean ifAround;

        //	private OnClickDelegator mDelegator;
        public CustomAdapter(Context ctx, List<PrepaidCard> sorts, boolean ifAround) {
            this.mInflater = LayoutInflater.from(ctx);
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
            ViewHolder holder;
            if (convertView == null) {
//                convertView = new CheckableItem(mContext, ifAround);
                convertView = mInflater.inflate(R.layout.layout_listview_itme_5_24,
                        null);
                holder = new ViewHolder();
                holder.textView1 = (TextView) convertView.findViewById(R.id.text1);
                holder.textView2 = (TextView) convertView.findViewById(R.id.text2);
                holder.textView3 = (TextView) convertView.findViewById(R.id.text3);
                convertView.setTag(holder);//绑定ViewHolder对象
            }
            else
            {
                holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象
            }

            PrepaidCard prepaidCard = sorts.get(position);
//            ((CheckableItem) convertView).setName(sort.getName());
//		((CheckableItem)convertView).setChecked(sort.isCheck());

            holder.textView1.setText(prepaidCard.getName());
            holder.textView2.setText(prepaidCard.getSort());
            holder.textView3.setText(prepaidCard.getShortName());

            return convertView;
        }
    }

    public  final class ViewHolder{
        public View view;
        public TextView textView1;
        public TextView textView2;
        public TextView textView3;
    }



}
