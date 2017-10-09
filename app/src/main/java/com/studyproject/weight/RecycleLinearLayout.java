package com.studyproject.weight;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * @author 李涛
 * @description
 * @Date 2017/9/28.
 */


public class RecycleLinearLayout  extends LinearLayoutManager {
    public RecycleLinearLayout(Context context) {
        super(context);
    }

    public RecycleLinearLayout(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        super.onMeasure(recycler, state, widthSpec, heightSpec);
    }
}
