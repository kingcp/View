package com.example.administrator.view.chapter06;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/11/15.
 */
public class PorterDuffXfermodeWavingView extends View {

    private static final String TAG = "PorterDuffXfermodeWavingView";
    private Paint mPaint;
    private Path mPath;
    private boolean isLeft;
    private int y;
    private int x;
    private int mWidth = 200;
    private int mHeight = 350;
    private int mPercent;

    public PorterDuffXfermodeWavingView(Context context) {
        this(context, null);
    }

    public PorterDuffXfermodeWavingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PorterDuffXfermodeWavingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setStrokeWidth(10);
        mPaint.setColor(Color.BLUE);

        mPath = new Path();
        mPaint.setAntiAlias(true);
        setLayerType(View.LAYER_TYPE_SOFTWARE,null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        System.out.println(TAG + " onMeasure");
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if(widthMode == MeasureSpec.EXACTLY)
            mWidth = widthSize;
        if(heightMode == MeasureSpec.EXACTLY)
            mHeight = heightSize;
        y = mHeight;
        setMeasuredDimension(mWidth,mHeight);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (y > -50) {
            y--;
        }
        if (x > 50) {
            isLeft = true;
        } else if (x < 0) {
            isLeft = false;
        }

        if (isLeft) {
            x = x - 1;
        } else {
            x = x + 1;
        }
        mPath.reset();
        mPath.moveTo(0, y);
        mPath.cubicTo(200 + x * 2, 250 + y, 300 + x * 2, y - 250, mWidth, y);
        mPath.lineTo(mWidth, mHeight);
        mPath.lineTo(0, mHeight);
        mPath.close();


  /*      //清除掉图像 不然path会重叠
        mBitmap.eraseColor(Color.parseColor("#00000000"));
        //dst
//        mCanvas.drawBitmap(bgBitmap,0,0,null);

//        mSRCPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));

        mCanvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2, mSRCPaint);

        mPaint.setXfermode(mMode);
        //src*/
        canvas.drawPath(mPath, mPaint);
        Bitmap bp = Bitmap.createBitmap(mWidth,mHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas1 = new Canvas(bp);
        mPaint.setColor(Color.RED);
        canvas1.drawCircle(mWidth / 2, mHeight / 2, Math.min(mHeight, mWidth) / 2, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bp, 0, 0, mPaint);
        mPaint.setXfermode(null);
        mPaint.setColor(Color.BLUE);
        postInvalidateDelayed(10);
    }

    /**
     * 在XML中定义了ID才能执行
     * @return
     */
    @Override
    protected Parcelable onSaveInstanceState() {
        System.out.println(TAG + " onSaveInstanceState");
        Bundle bundle = new Bundle();
        Parcelable superData = super.onSaveInstanceState();
        bundle.putParcelable("superData", superData);
        bundle.putInt("valueY", y);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        System.out.println(TAG + " onRestoreInstanceState");
        Bundle bundle = (Bundle) state;
        Parcelable superData = bundle.getParcelable("superData");
        y = bundle.getInt("valueY");
        super.onRestoreInstanceState(superData);
    }
}
