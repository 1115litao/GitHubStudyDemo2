package com.studyproject.weight;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

/**
 * @author 李涛
 * @description
 * @Date 2017/9/26.
 */


public class ScrollViewLayout extends ScrollView {

    public  ScrollListener listener;

    public  void setOnScrollViewLayoutListener(ScrollListener listener){
        this.listener = listener;
    }

    public interface ScrollListener{
          void ScrollY(int scrollY);
    }

    public ScrollViewLayout(Context context) {
        super(context);
    }

    public ScrollViewLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

  /*  @Override
    public void computeScroll() {
        super.computeScroll();
        if(listener!=null){
            listener.ScrollY(getScrollY());
        }
    }*/

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(listener!=null){
            listener.ScrollY(t);
        }
    }
}
