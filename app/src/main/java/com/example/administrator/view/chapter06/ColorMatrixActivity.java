package com.example.administrator.view.chapter06;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.administrator.view.R;

/**
 * Created by Administrator on 2016/11/10.
 */
public class ColorMatrixActivity extends Activity implements SeekBar.OnSeekBarChangeListener{

    private ImageView imageView;
    private SeekBar hueSeekBar,lumSeekBar,satSeekBar;
    private int hue;
    private float sat,lum;
    private Bitmap bitmap;
    private Paint paint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter06_color_matrix_layout);
        imageView = (ImageView) findViewById(R.id.imageView);
        hueSeekBar = (SeekBar) findViewById(R.id.hue_seekBar);
        lumSeekBar = (SeekBar) findViewById(R.id.lum_seekBar);
        satSeekBar = (SeekBar) findViewById(R.id.sat_seekBar);
        hueSeekBar.setOnSeekBarChangeListener(this);
        lumSeekBar.setOnSeekBarChangeListener(this);
        satSeekBar.setOnSeekBarChangeListener(this);
        hue = hueSeekBar.getProgress();
        sat = satSeekBar.getProgress() * 1.0F / 100;
        lum = lumSeekBar.getProgress() * 1.0F / 100;
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.color_matrix);
        imageView.setImageBitmap(bitmap);
        paint = new Paint();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()){
            case R.id.hue_seekBar:
                hue = progress;
            break;
            case R.id.sat_seekBar:
                sat = progress * 1.0F / 100;
            break;
            case R.id.lum_seekBar:
                lum = progress * 1.0F / 100;
            break;
        }
        Bitmap mBitmap = getColorMatrixBitmap(hue,sat,lum,bitmap);
        imageView.setImageBitmap(mBitmap);
    }

    private Bitmap getColorMatrixBitmap(int hue, float sat, float lum, Bitmap bitmap) {
        Bitmap bp = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bp);
       /* ColorMatrix hueColorMatrix = new ColorMatrix();
        hueColorMatrix.setRotate(0, hue);
        hueColorMatrix.setRotate(1, hue);
        hueColorMatrix.setRotate(2, hue);*/
        ColorMatrix satColorMatrix = new ColorMatrix();
        satColorMatrix.setSaturation(sat);
        ColorMatrix lumColorMatrix = new ColorMatrix();
        lumColorMatrix.setScale(lum, lum, lum, 1);
        ColorMatrix imageMatrix = new ColorMatrix();
//        imageMatrix.postConcat(hueColorMatrix);
        imageMatrix.postConcat(satColorMatrix);
        imageMatrix.postConcat(lumColorMatrix);
        paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));
        canvas.drawBitmap(bitmap,0,0,paint);
        return bp;

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
