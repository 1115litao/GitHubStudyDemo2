package com.demotest.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.demotest.R;

import java.util.ArrayList;

/**
 * @author 李涛
 * @description
 * @Date 2018/5/18.
 */


public class PhotoAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> allList = new ArrayList<>();
    public PhotoAdapter(Context context, ArrayList<String> allList) {
        this.context = context;
        this.allList = allList;
    }

    @Override
    public int getCount() {
        return allList.size() >0 ? allList.size():0;
    }

    @Override
    public Object getItem(int position) {
        return allList.get(position);
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
            convertView = View.inflate(context, R.layout.gv_item,null);
            holder.image = convertView.findViewById(R.id.gv_item_iv);
            convertView.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
        }
        holder.image.setImageURI(Uri.parse(allList.get(position)));
        return convertView;
    }

    class Holder{
        ImageView image;
    }
}
