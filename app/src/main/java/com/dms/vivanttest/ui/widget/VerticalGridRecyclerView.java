package com.dms.vivanttest.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;


public class VerticalGridRecyclerView extends RecyclerView {
    public VerticalGridRecyclerView(Context context) {
        super(context);
        init(context);
    }

    public VerticalGridRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public VerticalGridRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        GridLayoutManager layoutManager =
                new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
        setLayoutManager(layoutManager);
    }
}
