package com.example.administrator.view.chapter06;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.view.R;

/**
 * Created by Administrator on 2016/11/11.
 */
public class ColorMatrixTwoActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "ColorMatrixTwoActivity";
    private Button reset_btn,sure_btn;
    private ImageView imageView;
    private GridLayout gridlayout;
    private float[] colorMatrixArray = new float[20];
    private EditText[] colorMatrixEtArray = new EditText[20];
    private int mEditWidth,mEditHeight;
    private Bitmap bitmap;
    private Paint paint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.chapter06_color_matrix_two_layout);
        gridlayout = (GridLayout) findViewById(R.id.gridlayout);
        gridlayout.setRowCount(4);
        gridlayout.setColumnCount(5);
        imageView = (ImageView) findViewById(R.id.imageView);
        reset_btn = (Button) findViewById(R.id.reset_btn);
        sure_btn = (Button) findViewById(R.id.sure_btn);
        bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.color_matrix);
        paint = new Paint();
        imageView.setImageBitmap(bitmap);
        sure_btn.setOnClickListener(this);
        reset_btn.setOnClickListener(this);
        //post后的Runnable对象还是运行在UI线程中，所以太复杂是计算不能做。之所以这样做的原因是让布局完成后获得准确的mEditWidth，mEditHeight
        gridlayout.post(new Runnable() {
            @Override
            public void run() {
                mEditWidth = gridlayout.getMeasuredWidth() / 5;
                mEditHeight = gridlayout.getMeasuredHeight() / 4;
                for(int i = 0 ; i < 20 ; i++){
                    EditText et = new EditText(ColorMatrixTwoActivity.this);
                    colorMatrixEtArray[i] = et;
                    et.setWidth(mEditWidth);
                    et.setHeight(mEditHeight);
                    gridlayout.addView(et);
                    if(i % 6 == 0){
                        et.setText(String.valueOf(1));
                    }else{
                        et.setText(String.valueOf(0));
                    }
                }
                System.out.println(TAG + " " + Thread.currentThread().getName());
                Log.d(TAG, "run: " + Thread.currentThread().getName());
                Toast.makeText(ColorMatrixTwoActivity.this,Thread.currentThread().getName(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reset_btn:
                for(int i = 0 ; i < 20 ;i++){
                    if(i % 6 == 0){
                        colorMatrixArray[i] = 1;
                        colorMatrixEtArray[i].setText(String.valueOf(1));
                    }else{
                        colorMatrixArray[i] = 0;
                        colorMatrixEtArray[i].setText(String.valueOf(0));
                    }
                }
                imageView.setImageBitmap(bitmap);
                break;
            case R.id.sure_btn:
                for(int i = 0 ; i < 20 ; i++){
                    colorMatrixArray[i] = Float.valueOf(colorMatrixEtArray[i].getText().toString());
                }
                Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bmp);
                paint.setColorFilter(new ColorMatrixColorFilter(colorMatrixArray));
                canvas.drawBitmap(bitmap,0,0,paint);
                imageView.setImageBitmap(bmp);
                break;
        }
    }
}
