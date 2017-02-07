package com.example.chenlongjian.ycf_customview.demo5_56;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chenlongjian.ycf_customview.R;
import com.example.chenlongjian.ycf_customview.model.ListItem556;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by chenlongjian on 2017/2/6.
 */

public class Demo5_56Activity extends Activity {

    private ListView mListview;
    private List<ListItem556> datas = new ArrayList<ListItem556>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo5_56);


        mListview = (ListView) findViewById(R.id.listview556);

        initDate();

        mListview.setAdapter(new CommentAdapter(this, datas));

    }

    void initDate() {
        for (int i = 0; i < 20; i++) {
            ListItem556 itme = new ListItem556();
            itme.setQuestion("asdfasfalksjldfjsadlfgiuhwrigtopkoh;gkjnj,xcvn.,ZM:Lekjsrtfwbgjs" + i);
            itme.setAnswer("alkdjflkjauiwherflkwamskdfalsndkjfhalksdmlfjlskdf" + i);

            datas.add(itme);
        }
    }

    private class CommentAdapter extends BaseAdapter {

        private LayoutInflater mInflater;

        private List<ListItem556> lists;

        CommentAdapter(Context context, List<ListItem556> date) {
            mInflater = LayoutInflater.from(context);
            lists = date;
        }

        @Override
        public int getCount() {
            return lists.size();
        }

        @Override
        public Object getItem(int position) {
            return lists.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.listview_item_556,
                        parent, false);
                holder = new ViewHolder();
                holder.question = (TextView) convertView.findViewById(R.id.question_value);
                holder.answer = (TextView) convertView.findViewById(R.id.answer_value);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            ListItem556 item556 = lists.get(position);
            holder.question.setText(item556.getQuestion());
            holder.answer.setText(item556.getAnswer());

            return convertView;
        }
    }

    static class ViewHolder {
        TextView question;
        TextView answer;
    }
}
