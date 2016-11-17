package com.example.administrator.view.chapter06;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Administrator on 2016/11/16.
 */
public class SurfaceView01 extends SurfaceView implements SurfaceHolder.Callback,Runnable {

    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    private boolean mIsDrawing;
    private Paint paint;
    private Path path;
    private int x,y;
    private long start,end;


    public SurfaceView01(Context context) {
        this(context, null);
    }

    public SurfaceView01(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SurfaceView01(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mHolder = getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        path = new Path();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        mIsDrawing = false;
    }

    @Override
    public void run() {
        while (mIsDrawing){
            start = System.currentTimeMillis();
            x += 1;
            y = (int) (100*(Math.sin(x * 2 * Math.PI / 200 ))) + 400;
            path.lineTo(x,y);
            draw();
            end = System.currentTimeMillis();
            if(end - start > 100){
                try {
                    Thread.sleep(100-(end - start));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawPath(path,paint);
        }catch ( Exception e){
        }finally {
            if (mCanvas != null)
                mHolder.unlockCanvasAndPost(mCanvas);
        }
    }
}
