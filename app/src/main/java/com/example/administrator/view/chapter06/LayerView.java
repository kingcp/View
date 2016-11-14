package com.example.administrator.view.chapter06;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/11/9.
 */
public class LayerView extends View {

    private Paint mPaint;
    public LayerView(Context context) {
        this(context, null);
    }

    public LayerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LayerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor( Color.WHITE );
        mPaint.setColor( Color.BLUE );
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(200, 200, 80, mPaint);
        //这里的canvas.saveLayerAlpha()保存的是下一个图层，接下的绘画会在下个图层上实现
        canvas.saveLayerAlpha(0, 0, 400, 400, 127, Canvas.FULL_COLOR_LAYER_SAVE_FLAG);
        mPaint.setColor(Color.YELLOW);
        canvas.drawCircle(250, 250, 80, mPaint);
        // canvas.restore()将图层合并
        canvas.restore();
        mPaint.setColor(Color.RED);
        canvas.drawCircle(220, 120, 80, mPaint);

    }
}
