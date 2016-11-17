package com.example.administrator.view.chapter07_Animation;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

/**
 * Created by Administrator on 2016/11/17.
 */
public class Rotate3DAnimation extends Animation {

    private int centerWidth;
    private int centerHeight;
    private Camera camera = new Camera();

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        centerHeight = height / 2;
        centerWidth = width / 2;
        setFillAfter(true);
        setDuration(2000);
        setInterpolator(new LinearInterpolator());
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        final Matrix matrix = t.getMatrix();
        camera.save();
        camera.rotateY(360 * interpolatedTime);
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(centerWidth,centerHeight);
        matrix.postTranslate(-centerWidth,-centerHeight);

    }
}
