package com.studyproject.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.studyproject.R;
import com.studyproject.weight.CustomHeightViewPager;
import com.studyproject.weight.MyViewPager;

public class FragmentTwo extends Fragment {
    private int type;
    private MyViewPager viewPager;


    public FragmentTwo(int type, MyViewPager viewPager){
        this.type = type;
        this.viewPager = viewPager;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getActivity(),R.layout.fragment_fragment_two,null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        TextView showsize = (TextView) view.findViewById(R.id.showsize);
        LinearLayout content_Layout = (LinearLayout) view.findViewById(R.id.content_layout2);
        showsize.setText("该页面的的高度为");
     /*   int[] num = new int[2];
        content_Layout.getLocationOnScreen(num);
        int height = num[1];*/

        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        content_Layout.measure(w, h);
        int height = content_Layout.getMeasuredHeight();
        int width = content_Layout.getMeasuredWidth();
        viewPager.calculate(type,height);

        Log.i("aaa","该页面的的高度为Two"+height);
    }

}
