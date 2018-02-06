package com.rxjavatest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rxjavatest.R;
import com.rxjavatest.bean.HomeResults;
import com.rxjavatest.bean.MessageDataBean;

/**
 * @author 李涛
 * @description
 * @Date 2018/1/11.
 */


public class HomeRecyAdapter extends RecyclerView.Adapter<HomeRecyAdapter.Holder> implements View.OnClickListener{

    private OnClickListenerR onClickListenerR;
    private  Context context;
    private  MessageDataBean messageDataBean = new MessageDataBean();
    public  HomeRecyAdapter(Context context){
        this.context = context;
    }


    public void  updateData(MessageDataBean messageDataBean){
            this.messageDataBean = messageDataBean;
            notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_recy,null);
        view.setOnClickListener(this);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
                holder.itemView.setTag(position);
                holder.item_home_text.setText(messageDataBean.getResults().get(position).getDesc());
                holder.item_home_timer.setText(messageDataBean.getResults().get(position).getCreatedAt());
                if(!TextUtils.isEmpty(messageDataBean.getResults().get(position).getWho())||"null".equals(messageDataBean.getResults().get(position).getWho())){
                    holder.item_home_author.setText("Author："+messageDataBean.getResults().get(position).getWho());
                }
    }



    @Override
    public int getItemCount() {
        return messageDataBean.getResults().size()>0?messageDataBean.getResults().size():0;
    }

    @Override
    public void onClick(View v) {
        onClickListenerR.Onclick(v,(int)v.getTag(),messageDataBean.getResults().get((int)v.getTag()));
    }

    public  class  Holder extends RecyclerView.ViewHolder{
        //时间
        private final TextView item_home_timer;
        //主题
        private final TextView item_home_text;
        //作者
        private final TextView item_home_author;
        //背景图
//        private final ImageView item_home_image;

        public Holder(View itemView) {
            super(itemView);
            item_home_timer = itemView.findViewById(R.id.item_home_timer);
            item_home_author = itemView.findViewById(R.id.item_home_author);
            item_home_text = itemView.findViewById(R.id.item_home_text);
//            item_home_image = itemView.findViewById(R.id.item_home_image);
        }
    }

   public interface OnClickListenerR{
        void Onclick(View  view,int position,HomeResults homeResults);
    }

    public  void setOnClickRecycleListener(OnClickListenerR onClickListenerR){
        this.onClickListenerR = onClickListenerR;
    }



}
