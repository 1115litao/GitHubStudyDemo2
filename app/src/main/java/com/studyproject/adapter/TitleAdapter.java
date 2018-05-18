package com.studyproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.studyproject.R;

import java.util.ArrayList;

/**
 * @author 李涛
 * @description
 * @Date 2018/3/1.
 */


public class TitleAdapter  extends RecyclerView.Adapter<TitleAdapter.Holder2> {

    private  Context context;
    private  ArrayList<String> list;

    public  TitleAdapter(Context context, ArrayList<String> list){
        this.context = context;
        this.list = list;
    }
    @Override
    public Holder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.title_item,null);
        return new Holder2(view);
    }

    @Override
    public void onBindViewHolder(Holder2 holder, int position) {
            holder.tv.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class Holder2  extends RecyclerView.ViewHolder{

        private   TextView tv;

        public Holder2(View itemView) {
            super(itemView);
              tv = (TextView) itemView.findViewById(R.id.item_name);
        }
    }
}
