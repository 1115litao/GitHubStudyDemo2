package com.rxjavatest.weight;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import com.rxjavatest.R;
import com.rxjavatest.adapter.RecycleBaseAdapter;

import java.util.List;

/**
 * @author 李涛
 * @description
 * @Date 2018/2/8.
 */


public class RecycleCustom extends RecyclerView{
   private List<Object> list;
    private int lastItem;
    private int firstItem;
    private      int[] lastInto  = new int[]{};
    private      int[] firstInto  = new int[]{};
    private int itemCount;
    private RecycleBaseAdapter adapter;

    public RecycleCustom(Context context) {
        super(context);
    }

    public RecycleCustom(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
            itemCount = getLayoutManager().getItemCount();
            getLastIttem();
    }


    public  void setAdapter(RecycleBaseAdapter adapter, List<Object> list){
        this.list = list;
        this.adapter = adapter;
        setAdapter(adapter);
    }

//    由于RecyclerView能实现LinearLayoutManager、GridLayoutManager与StaggeredGridLayoutManager不同的布局，所以另外两个要根据不同的manager来获取,还是看具体代码吧
    private void getLastIttem(){
        if(getLayoutManager()instanceof LinearLayoutManager){
            lastItem = ((LinearLayoutManager)getLayoutManager()).findLastVisibleItemPosition();
            firstItem = ((LinearLayoutManager)getLayoutManager()).findFirstVisibleItemPosition();
        }else {
            lastInto = ((StaggeredGridLayoutManager)getLayoutManager()).findLastVisibleItemPositions(lastInto);
            firstInto = ((StaggeredGridLayoutManager)getLayoutManager()).findFirstVisibleItemPositions(firstInto);
            lastItem = lastInto[0];
            firstItem = firstInto[0];
        }
    }


    public void setHeaderView(Context context){
        View headerView = LayoutInflater.from(context).inflate(R.layout.header_view,this,false);
        adapter.setHeaderView(headerView);
    }
    public void setFootView(Context context){
        View footView = LayoutInflater.from(context).inflate(R.layout.food_view,this,false);
        adapter.setFootView(footView);
    }

}
