package com.studyproject;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.studyproject.adapter.VPAdapter;
import com.studyproject.fragment.FragmentOne;
import com.studyproject.fragment.FragmentTwo;
import com.studyproject.weight.MyViewPager;
import com.studyproject.weight.ScrollViewLayout;

import java.util.ArrayList;
import java.util.List;

public class SV_Activity extends AppCompatActivity implements ScrollViewLayout.ScrollListener {

    private MyViewPager customvp;
    private LinearLayout ll_tablayout;
    private LinearLayout ll_tablayout2;
    private List<Fragment> list;
    private ScrollViewLayout parentScrollView;
    private int tabTop;
    private boolean isShow;
    private View tabView;
    private PopupWindow popupWindow;
    private TextView title_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sv_);
        initView();
    }

    private void initView() {
        if(tabView==null){
            tabView = LayoutInflater.from(SV_Activity.this).inflate(R.layout.tab_layout,null);
            TextView tab = (TextView) tabView.findViewById(R.id.tab);
         //   tab.setBackgroundColor(Color.parseColor("#FF4081"));
        }

        title_layout = (TextView) findViewById(R.id.title_layout);

        popupWindow = new PopupWindow(tabView, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,false);
        popupWindow.setAnimationStyle(R.style.popupWindowAnim);
//        mainview = (FrameLayout) findViewById(R.id.mainview);
        customvp = (MyViewPager) findViewById(R.id.customvp);
        ll_tablayout = (LinearLayout) findViewById(R.id.ll_tablayout);
        ll_tablayout2 = (LinearLayout) findViewById(R.id.ll_tablayout2);
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
        tabTop = ll_tablayout.getTop();

        Log.i("aaa","scrollY"+scrollY);
        Log.i("aaa","tabTop"+tabTop);


        if(scrollY>tabTop){
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
        ll_tablayout2.setVisibility(View.GONE);
        isShow =false;
    }

    private  void   showWindow(){
        ll_tablayout2.setVisibility(View.VISIBLE);
        isShow = true;

    }

}
