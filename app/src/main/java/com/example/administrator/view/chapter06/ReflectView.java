package com.example.administrator.view.chapter06;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.view.R;

/**
 * Created by Administrator on 2016/11/15.
 */
public class ReflectView extends View {

    private Bitmap srcBitmap;
    private Bitmap reflectBitmap;
    private Paint paint;
    private float width = 300,height = 320;

    public ReflectView(Context context) {
        this(context, null);
    }

    public ReflectView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ReflectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        srcBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.reflect);
        Matrix matrix = new Matrix();
        float x = width / srcBitmap.getWidth();
        float y = height / srcBitmap.getHeight();
        matrix.setScale(x,y);
        srcBitmap = Bitmap.createBitmap(srcBitmap,0,0,srcBitmap.getWidth(),srcBitmap.getHeight(),matrix,false);
        //倒影
        matrix.setScale(1f, -1f);
        reflectBitmap = Bitmap.createBitmap(srcBitmap,0,0,srcBitmap.getWidth(),srcBitmap.getHeight(),matrix,false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension((int)width,(int)height*2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(srcBitmap, 0, 0, null);
        canvas.drawBitmap(reflectBitmap, 0, srcBitmap.getHeight(), null);
        paint.setShader(new LinearGradient(0, srcBitmap.getHeight(), 0, srcBitmap.getHeight() + srcBitmap.getHeight(), 0xDDffffff, 0x00ffffff, Shader.TileMode.CLAMP));
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        paint.setColor(Color.BLACK);
        canvas.drawRect(0, srcBitmap.getHeight(), srcBitmap.getWidth(), srcBitmap.getHeight() * 2, paint);
        paint.setXfermode(null);
    }
}
