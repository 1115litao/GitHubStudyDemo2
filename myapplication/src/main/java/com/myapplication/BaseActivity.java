package com.myapplication;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


    }
    public  void setFitsSystemWindows( boolean value) {
        ViewGroup contentFrameLayout = findViewById(android.R.id.content);
        View parentView = contentFrameLayout.getChildAt(0);
        if (parentView != null && Build.VERSION.SDK_INT >= 14) {
            parentView.setFitsSystemWindows(value);
            if (parentView instanceof DrawerLayout) {
                DrawerLayout drawer = (DrawerLayout) parentView;
                //将主页面顶部延伸至status bar;虽默认为false,但经测试,DrawerLayout需显示设置
                 drawer.setClipToPadding(false);
            }
        }

        View statusBarView = new View(this);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight());
        statusBarView.setBackgroundColor(Color.parseColor("#ff403b"));
        contentFrameLayout.addView(statusBarView, lp);


    }


    public int getStatusBarHeight() {
        int result = 0;
        //获取状态栏高度的资源  id
       int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
       if (resourceId > 0) {
           result = getResources().getDimensionPixelSize(resourceId);
       } return result;
    }

}



