package com.studyproject.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

/**
 * @author 李涛
 * @description
 * @Date 2017/9/26.
 */


public class ScrollViewLayout extends ScrollView {

    public  ScrollListener listener;
    private int  touchSlop;
    private int dowX;
    private int dowY;

    public  void setOnScrollViewLayoutListener(ScrollListener listener){
        this.listener = listener;
    }

    public interface ScrollListener{
          void ScrollY(int scrollY);
    }

    public ScrollViewLayout(Context context) {
        super(context);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public ScrollViewLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public ScrollViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(listener!=null){
            listener.ScrollY(getScrollY());
        }
    }


 /*   @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        int action = ev.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                dowX = (int) ev.getRawX();
                dowY = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) ev.getRawY();
                if(Math.abs(moveY-dowY)<touchSlop){
                        return true;
                }
                break;
        }



        return super.onInterceptTouchEvent(ev);
    }*/
}
