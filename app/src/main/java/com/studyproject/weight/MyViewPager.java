package com.studyproject.weight;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 李涛
 * @description
 * @Date 2017/9/18.
 */


public class MyViewPager extends ViewPager {
    private Map<Integer, Integer> maps = new HashMap<Integer, Integer>();
    private int current;
    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = 0;
        if(maps.size() > current){
            height = maps.get(current + 1);
        }
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void resetHeight(int current){
        this.current = current;
        if(maps.size() > current){
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
            if(layoutParams == null){
                layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, maps.get(current + 1));
            }else{
                layoutParams.height = maps.get(current + 1);
            }
            setLayoutParams(layoutParams);
        }
    }

    public void calculate(int type, int height){
        maps.put(type, height);
        Log.i("aaa","添加的时候"+maps.size());
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int  action = ev.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:


                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
