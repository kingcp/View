package com.example.administrator.view.chapter07_Animation;

import android.graphics.Matrix;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by Administrator on 2016/11/17.
 */
public class CloseTvAnimation extends Animation {

    private int mCurrentWidth,mCurrentHeight;
    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        mCurrentWidth = width / 2;
        mCurrentHeight = height / 2;
        setInterpolator(new AccelerateDecelerateInterpolator());
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        final Matrix matrix = t.getMatrix();
        matrix.preScale(1 - interpolatedTime, 1 - interpolatedTime, mCurrentWidth, mCurrentHeight);
    }

}
