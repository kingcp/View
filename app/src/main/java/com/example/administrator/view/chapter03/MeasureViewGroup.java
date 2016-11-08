package com.example.administrator.view.chapter03;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/11/8.
 */
public class MeasureViewGroup extends ViewGroup {

    public MeasureViewGroup(Context context) {
        this(context, null);
    }

    public MeasureViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MeasureViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for(int i = 0 ; i < childCount ; i++){
            View childView = getChildAt(i);
            childView.getMeasuredHeight();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
