package com.studyproject.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.studyproject.R;
import com.studyproject.adapter.RecycleAdatper;
import com.studyproject.weight.CustomHeightViewPager;
import com.studyproject.weight.MyViewPager;


@SuppressLint("ValidFragment")
public class FragmentOne extends Fragment {


    private RecyclerView fragment_list;
    private int type;
    private MyViewPager viewPager;

    private LinearLayout content_layout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = View.inflate(getActivity(),R.layout.fragment_fragment_one,null);
        initView(view);
        return view;
    }

    public   FragmentOne(int type, MyViewPager viewPager){
                this.type = type;
                this.viewPager = viewPager;
    }

    private void initView(View  view) {
       content_layout = (LinearLayout) view.findViewById(R.id.content_layout);

        fragment_list = (RecyclerView) view.findViewById(R.id.fragment_list);
        fragment_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        fragment_list.setAdapter(new RecycleAdatper(getActivity()));
        //解决RecycleView在ScrollView内滑动卡顿
        fragment_list.setNestedScrollingEnabled(false);

        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        content_layout.measure(w, h);
        int height = content_layout.getMeasuredHeight();
        int width = content_layout.getMeasuredWidth();

        viewPager.calculate(type,height);
    }


}
