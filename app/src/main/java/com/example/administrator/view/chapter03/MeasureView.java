package com.example.administrator.view.chapter03;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/11/8.
 */
public class MeasureView extends TextView {

    private Paint mPaint1;
    private Paint mPaint2;

    public MeasureView(Context context) {
        this(context, null);
    }

    public MeasureView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MeasureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint1 = new Paint();
        mPaint1.setStyle(Paint.Style.STROKE);
        mPaint1.setStrokeWidth(5);
        mPaint1.setColor(Color.RED);
        mPaint2 = new Paint();
        mPaint2.setStyle(Paint.Style.STROKE);
        mPaint2.setStrokeWidth(10);
        mPaint2.setColor(Color.BLUE);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 默认只支持EXACTLY,为了支持AT_MOST模式，需要以下操作
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int resultWidth = 720;
        int resultHeight = 1080;
        if(widthMode == MeasureSpec.EXACTLY){
            resultWidth = widthSize;
        }else if(widthMode == MeasureSpec.AT_MOST){
            resultWidth = Math.min(resultWidth,resultWidth);
        }

        if(heightMode == MeasureSpec.EXACTLY){
            resultHeight = heightSize;
        }else if(heightMode == MeasureSpec.AT_MOST){
            resultHeight = Math.min(resultHeight,heightSize);
        }
        setMeasuredDimension(resultWidth, resultHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint1);
        canvas.drawRect(10,10,getMeasuredWidth()-10,getMeasuredHeight()-10,mPaint2);
        super.onDraw(canvas);

    }
}

