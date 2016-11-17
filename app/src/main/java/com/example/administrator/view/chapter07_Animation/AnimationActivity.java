package com.example.administrator.view.chapter07_Animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.view.R;

/**
 * Created by Administrator on 2016/11/17.
 */
public class AnimationActivity extends Activity {

    private ImageView imageView;
    private Button close_btn;
    private  CloseTvAnimation animation;
    private Button rotate_btn;
    private Rotate3DAnimation rotate3DAnimation;

    private LinearLayout ll_header;
    private LinearLayout ll_hidden;
    private int hiddenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter07_animation_layout);
        animation = new CloseTvAnimation();
        rotate3DAnimation = new Rotate3DAnimation();
        animation.setDuration(2000);
        imageView = (ImageView) findViewById(R.id.image_view);
        close_btn = (Button) findViewById(R.id.close_btn);
        rotate_btn = (Button) findViewById(R.id.rotate_btn);
        ll_header = (LinearLayout) findViewById(R.id.ll_header);
        ll_hidden = (LinearLayout) findViewById(R.id.ll_hidden);

        hiddenHeight = (int) (50 * getResources().getDisplayMetrics().density + 0.5);

        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.startAnimation(animation);
            }
        });
        rotate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.startAnimation(rotate3DAnimation);
            }
        });
        ll_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ll_hidden.getVisibility() == View.GONE){
                    ll_hidden.setVisibility(View.VISIBLE);
                    ValueAnimator valueAnimator = ValueAnimator.ofInt(0,hiddenHeight);
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            int height = (int) animation.getAnimatedValue();
                            ViewGroup.LayoutParams params = ll_hidden.getLayoutParams();
                            params.height = height;
                            ll_hidden.setLayoutParams(params);
                        }
                    });
                    valueAnimator.start();
                }else{
                    ValueAnimator valueAnimator = ValueAnimator.ofInt(hiddenHeight,0);
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            int height = (int) animation.getAnimatedValue();
                            ViewGroup.LayoutParams params = ll_hidden.getLayoutParams();
                            params.height = height;
                            ll_hidden.setLayoutParams(params);
                        }
                    });
                    valueAnimator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            ll_hidden.setVisibility(View.GONE);
                            super.onAnimationEnd(animation);
                        }
                    });
                    valueAnimator.start();
                }
            }
        });



    }
}
