package com.example.administrator.view.chapter06;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.administrator.view.R;

/**
 * Created by Administrator on 2016/11/10.
 */
public class ColorMatrixImageView extends ImageView {

    private Paint paint;
    private int hue;
    private float sat,lum;
    private Bitmap bitmap;

    public ColorMatrixImageView(Context context) {
        this(context, null);
    }

    public ColorMatrixImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorMatrixImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.color_matrix);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        ColorMatrix hueColorMatrix = new ColorMatrix();
        hueColorMatrix.setRotate(0, hue);
        hueColorMatrix.setRotate(1, hue);
        hueColorMatrix.setRotate(2, hue);
        ColorMatrix satColorMatrix = new ColorMatrix();
        satColorMatrix.setSaturation(sat);
        ColorMatrix lumColorMatrix = new ColorMatrix();
        lumColorMatrix.setScale(lum, lum, lum, 1);
        ColorMatrix imageMatrix = new ColorMatrix();
        imageMatrix.postConcat(hueColorMatrix);
        imageMatrix.postConcat(satColorMatrix);
        imageMatrix.postConcat(lumColorMatrix);
        paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));
        Bitmap bp = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight());
        canvas.drawBitmap(bp,0,0,paint);
    }

    public void invalidateView(int hue, float sat , float lum){
        this.hue = hue;
        this.sat = sat;
        this.lum = lum;
        postInvalidate();
    }
}
