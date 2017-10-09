package com.studyproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.studyproject.R;

/**
 * @author 李涛
 * @description
 * @Date 2017/9/27.
 */


public class RecycleAdatper  extends RecyclerView.Adapter<RecycleAdatper.Holder>{

   private Context context;

    public RecycleAdatper(Context context) {
        this.context = context;

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.recycle_item,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        holder.tv.setText("这个页面的高度是");
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "sssssssssssssssssssss"+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class  Holder extends RecyclerView.ViewHolder{

        private final TextView tv;

        public Holder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.recycle_item_tv);
        }
    }
}
