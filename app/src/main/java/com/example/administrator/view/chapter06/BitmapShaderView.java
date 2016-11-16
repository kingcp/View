package com.example.administrator.view.chapter06;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.view.R;

/**
 * Created by Administrator on 2016/11/15.
 */
public class BitmapShaderView extends View {

    private Paint paint;
    private Bitmap bitmap;
    private int width = 200,height = 200;

    public BitmapShaderView(Context context) {
        this(context, null);
    }

    public BitmapShaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BitmapShaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.color_matrix);
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(shader);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
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
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(width / 2 , height / 2 , Math.min(width,height) / 2 , paint);
    }
}
