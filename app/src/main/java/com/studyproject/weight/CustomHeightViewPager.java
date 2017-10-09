package com.studyproject.weight;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 李涛
 * @description
 * @Date 2017/9/26.
 */


public class CustomHeightViewPager extends ViewPager {
    private int current ;
    private Map<Integer,Integer> maps = new HashMap<>();
    public CustomHeightViewPager(Context context) {
        super(context);
    }

    public CustomHeightViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int height = 0;
        Log.i("aaa","onMeasure---------current"+current);
        Log.i("aaa","onMeasure---------maps.size()"+maps.size());
        if(maps.size() > current){
            height = maps.get(current + 1);
        }
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public  void customHeight( int current){
        this.current = current;
        Log.i("aaa","current"+current);
        Log.i("aaa","maps.size()"+maps.size());
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

    public  void calculate(int type ,int height){
         maps.put(type,height);
    }
}
