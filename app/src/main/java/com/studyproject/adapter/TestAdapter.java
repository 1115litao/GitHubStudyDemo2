package com.studyproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.studyproject.R;

import java.util.ArrayList;

/**
 * @author 李涛
 * @description
 * @Date 2018/3/23.
 */


public class TestAdapter  extends BaseAdapter{
    Context context;
    ArrayList<String> list;
    public TestAdapter(Context context, ArrayList<String> list) {
        this.list = list;
        this.context  = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView==null){
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_test,null,false);
            holder.item_textview = (TextView) convertView.findViewById(R.id.item_textview);
            convertView.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
        }

        holder.item_textview.setText(list.get(position));
        return convertView;
    }

    class  Holder {
        private TextView item_textview;
    }
}
