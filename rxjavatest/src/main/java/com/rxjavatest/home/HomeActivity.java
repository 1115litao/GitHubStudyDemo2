package com.rxjavatest.home;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.rxjavatest.R;
import com.rxjavatest.adapter.HomeRecyAdapter;
import com.rxjavatest.base.LT_BaseActivity;
import com.rxjavatest.bean.HomeResults;
import com.rxjavatest.bean.MessageDataBean;
import com.rxjavatest.webview.CustomWebView;


public class HomeActivity extends LT_BaseActivity implements HomeItf.homeView{

    /**数据初始化*/
    private HomeControl homeControl;
    /**正文内列表*/
    private RecyclerView homeList;
    /**数据适配器*/
    private HomeRecyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_home);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        //初始化数据
        homeControl = new HomeControl(this);
        //初始化RecycleView数据
        homeControl.getNetData();
    }


    @Override
    public void initView() {
        homeList = findViewById(R.id.home_list);
        homeList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HomeRecyAdapter(this);
        homeList.setAdapter(adapter);
    }


    @Override
    public void initListener() {
           adapter.setOnClickRecycleListener(new HomeRecyAdapter.OnClickListenerR() {
               @Override
               public void Onclick(View view, int position, HomeResults homeResults) {
                   Intent intent = new Intent(HomeActivity.this, CustomWebView.class);
                   intent.putExtra("weburl",""+homeResults.getUrl());
                   startActivity(intent);
               }
           });
    }


    /**
     * 更新网络数据
     * @param data
     */
    @Override
    public void hettpNetData(MessageDataBean data) {
        //适配RecycleView
        adapter.updateData(data);
    }
}
