package com.example.administrator.view.chapter06;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.administrator.view.R;

/**
 * Created by Administrator on 2016/11/16.
 */
public class PorterDuffXfermodeGGKView extends View {

    private Paint paint;
    private Path path;
    private Bitmap bitmap;
    private Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.XOR);
    private int width = 200,height = 100;
    private Matrix matrix = new Matrix();
    private Bitmap outBitmap;
    private Canvas canvas1;

    public PorterDuffXfermodeGGKView(Context context) {
        this(context, null);
    }

    public PorterDuffXfermodeGGKView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PorterDuffXfermodeGGKView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setXfermode(xfermode);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        path = new Path();
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.reflect);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if(widthMode == MeasureSpec.EXACTLY)
            width = widthSize;
        if(heightMode == MeasureSpec.EXACTLY)
            height = heightSize;
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        float x = (float)width / bitmap.getWidth();
        float y = (float)height / bitmap.getHeight();
        matrix.setScale(x,y);
        bitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,false);
        outBitmap = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        canvas1 = new Canvas(outBitmap);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        canvas1.drawPath(path,paint);
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, 0, null);

        canvas1.drawColor(Color.LTGRAY);
        canvas1.drawPath(path,paint);
        canvas.drawBitmap(outBitmap,0,0,null);
    }
}
