package com.example.administrator.view.chapter06;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.view.R;

/**
 * Created by Administrator on 2016/11/14.
 */
public class PorterDuffXfermodeView01 extends View {

    private Bitmap bitmap;
    private Paint paint;

    public PorterDuffXfermodeView01(Context context) {
        this(context, null);
    }

    public PorterDuffXfermodeView01(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PorterDuffXfermodeView01(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.color_matrix);
        setLayerType(View.LAYER_TYPE_SOFTWARE,null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bp = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);



        Canvas canvas2 = new Canvas(bp);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        canvas2.drawRoundRect(10, 10, bp.getWidth()-10, bp.getHeight()-10, 80, 80, paint);

        canvas.drawBitmap(bitmap, 0, 0, null);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(bp,0,0,paint);



    }
}
