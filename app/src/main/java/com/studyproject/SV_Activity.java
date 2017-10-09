package com.studyproject;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.studyproject.adapter.VPAdapter;
import com.studyproject.fragment.FragmentOne;
import com.studyproject.fragment.FragmentTwo;
import com.studyproject.weight.CustomHeightViewPager;
import com.studyproject.weight.MyViewPager;
import com.studyproject.weight.ScrollViewLayout;

import java.util.ArrayList;
import java.util.List;

public class SV_Activity extends AppCompatActivity implements ScrollViewLayout.ScrollListener {

    private MyViewPager customvp;
    private LinearLayout ll_tablayout;
    private List<Fragment> list;
    private ScrollViewLayout parentScrollView;
    private  int[] location= new int[2];
    private int scrollTop;
    private int tabTop;
    private boolean isShow;
    private View tabView;
    private PopupWindow popupWindow;
    private LinearLayout mainview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sv_);
        initView();
    }

    private void initView() {
        mainview = (LinearLayout) findViewById(R.id.mainview);
        customvp = (MyViewPager) findViewById(R.id.customvp);
        ll_tablayout = (LinearLayout) findViewById(R.id.ll_tablayout);
        parentScrollView = (ScrollViewLayout) findViewById(R.id.parentScrollView);
        parentScrollView.setOnScrollViewLayoutListener(this);
        list = new ArrayList();
        list.add(new FragmentOne(1,customvp));
        list.add(new FragmentTwo(2,customvp));
        customvp.setAdapter(new VPAdapter(getSupportFragmentManager(),list));
        customvp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                        customvp.resetHeight(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

    @Override
    public void ScrollY(int scrollY) {
        parentScrollView.getLocationOnScreen(location);
        scrollTop = location[1];

        tabTop = ll_tablayout.getTop();
        if(scrollY>=tabTop){
            if(!isShow){
                showWindow();
            }
        }else{
            if (isShow){
                removeWindow();
            }
        }
    }


    private void removeWindow() {
        if(popupWindow!=null){
            popupWindow.dismiss();
            isShow =false;
        }
    }

    private  void   showWindow(){
        if(tabView==null){
            tabView = LayoutInflater.from(SV_Activity.this).inflate(R.layout.tab_layout,null);
        }
        popupWindow = new PopupWindow(tabView, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,false);
        popupWindow.showAtLocation(mainview, Gravity.NO_GRAVITY,0,scrollTop);
        isShow = true;
    }

}
