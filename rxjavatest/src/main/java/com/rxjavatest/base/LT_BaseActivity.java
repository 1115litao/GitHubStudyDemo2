package com.rxjavatest.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

public abstract class LT_BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
        initListener();
    }
    /**初始化数据*/
        public abstract   void  initData();
    /**初始化布局*/
        public abstract   void  initView();

    /**初始化监听*/
        public abstract   void  initListener();


}
