package com.example.administrator.view.chapter06;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.view.R;

/**
 * Created by Administrator on 2016/11/14.
 */
public class FlagWavingView extends View{

    private static final String TAG = "FlagWavingView";
    private Bitmap mBitmap;
    private static final int WIDTH = 200;
    private static final int HEIGHT = 100;
    private Paint paint;
    private float[] verts = new float[(WIDTH + 1) *( HEIGHT + 1) * 2];
    private float[] orig = new float[(WIDTH + 1) *( HEIGHT + 1) * 2];
    private static final int A = 50;

    public FlagWavingView(Context context) {
        this(context, null);
    }

    public FlagWavingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlagWavingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.color_matrix);
        int bpWidth = mBitmap.getWidth();
        int bpHeigth = mBitmap.getHeight();
        int index = 0;
        for(int i = 0 ; i <= HEIGHT ; i++){
            for(int j = 0 ; j <= WIDTH ; j++){
                orig[index * 2 + 0 ]  = verts[index * 2 + 0 ] = j * bpWidth / WIDTH;
                orig[index * 2 + 1 ] = i * bpHeigth / HEIGHT;
                float sin = (float) Math.sin( ((float) j / WIDTH ) * 2 * Math.PI );
                verts[index * 2 + 1 ] = orig[index * 2 + 1 ] + sin * A;
                index++;
            }
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(100);
                        int index = 0;
                        for (int i = 0; i <= HEIGHT; i++) {
                            for (int j = 0; j <= WIDTH; j++) {
                                verts[index * 2 + 0] = orig[index * 2 + 0];
                                float sin = (float) Math.sin(((float) j / WIDTH) * 2 * Math.PI + k * 0.1 * Math.PI);
                                verts[index * 2 + 1] = orig[index * 2 + 1] + sin * A;
                                index++;
                            }
                        }
                        postInvalidate();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top - 100, right, bottom + 50);
    }

    /**
     * 自己的布局
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t-100, r, b+100);
    }

    public static int k = 0;
    @Override
    protected void onDraw(Canvas canvas) {
        k++;
        canvas.drawBitmapMesh(mBitmap, WIDTH, HEIGHT, verts, 0, null, 0, paint);
        super.onDraw(canvas);

    }
}
