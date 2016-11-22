package com.example.administrator.view.chapter05_scroll;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2016/11/22.
 */
public class DrawLayout extends FrameLayout {

    private ViewDragHelper mViewDragHelper;
    private View mumeView,contentView;
    private int mWidth;
    private static final String TAG = "DrawLayout";


    public DrawLayout(Context context) {
        this(context, null);
    }

    public DrawLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mViewDragHelper = ViewDragHelper.create(this, callback);
    }

    private ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return contentView == child;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return left;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return 0;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            System.out.println(TAG + "  releasedChild.getLeft() = " + releasedChild.getLeft());
            if(releasedChild.getLeft() > mWidth / 2){
                mViewDragHelper.smoothSlideViewTo(contentView, mWidth, 0);
                ViewCompat.postInvalidateOnAnimation(DrawLayout.this);
            }else{
                mViewDragHelper.smoothSlideViewTo(contentView,0,0);
                ViewCompat.postInvalidateOnAnimation(DrawLayout.this);
            }
        }
    };

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mumeView = getChildAt(0);
        contentView = getChildAt(1);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = mumeView.getMeasuredWidth();
        System.out.println(TAG + " mWidth = " + mWidth);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        if(mViewDragHelper.continueSettling(true)){
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
