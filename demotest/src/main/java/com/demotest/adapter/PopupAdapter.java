package com.demotest.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.demotest.MainActivity;
import com.demotest.R;

import java.util.ArrayList;

/**
 * @author 李涛
 * @description
 * @Date 2018/5/3.
 */


public class PopupAdapter  extends BaseAdapter{
   private   Context context;
   private ArrayList<String> list;
    public PopupAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
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
        if(convertView == null){
            holder = new Holder();
            convertView = View.inflate(context, R.layout.item,null);
            holder.tv = convertView.findViewById(R.id.tv_item_text);
            convertView.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
        }
        holder.tv.setText(list.get(position));
        return convertView;
    }

    class Holder{
        TextView tv;
    }
}
