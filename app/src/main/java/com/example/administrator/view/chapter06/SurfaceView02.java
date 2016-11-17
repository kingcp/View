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
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.administrator.view.R;

/**
 * Created by Administrator on 2016/11/16.
 */
public class SurfaceView02 extends SurfaceView implements Runnable, SurfaceHolder.Callback {

    private Paint paint;
    private Path path;
    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    private boolean isDrawing;
    private long start,end;
    private Bitmap mBitmap;
    private int mWidth,mHeight;
    private static final String TAG = "SurfaceView02";

    public SurfaceView02(Context context) {
        this(context, null);
    }

    public SurfaceView02(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SurfaceView02(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.reflect);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        System.out.println(TAG + " onFinishInflate");
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if(widthMode == MeasureSpec.EXACTLY){
            mWidth = widthSize;
        }else{
            mWidth = 500;
        }
        if(heightMode == MeasureSpec.EXACTLY){
            mHeight = heightSize;
        }else{
            mHeight = 500;
        }
        setMeasuredDimension(mWidth, mHeight);
        System.out.println(TAG + " onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Matrix matrix = new Matrix();
        float x = (float)mWidth / mBitmap.getWidth();
        float y = (float)mHeight / mBitmap.getHeight();
        matrix.setScale(x,y);
        mBitmap = Bitmap.createBitmap(mBitmap,0,0,mBitmap.getWidth(),mBitmap.getHeight(),matrix,false);
        System.out.println(TAG + " onLayout");
    }

    private void initView() {
        mHolder = getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
        paint = new Paint();
        path = new Path();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(30);
        paint.setColor(Color.BLACK);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isDrawing = true;
        new Thread(this).start();
        System.out.println(TAG + " surfaceCreated");

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        System.out.println(TAG + " surfaceChanged");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isDrawing = false;
        System.out.println(TAG + " surfaceDestroyed");
    }

    @Override
    public void run() {
        while (isDrawing){
            start = System.currentTimeMillis();
            draw();
            end = System.currentTimeMillis();
            if (end - start < 100){
                try {
                    Thread.sleep((100 - (end - start)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();
            mCanvas.drawBitmap(mBitmap,0,0,null);
            mCanvas.drawPath(path, paint);
        }catch (Exception e){
        }finally {
            if(mCanvas != null)
                mHolder.unlockCanvasAndPost(mCanvas);
        }
    }
}
