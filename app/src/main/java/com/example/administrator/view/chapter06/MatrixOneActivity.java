package com.example.administrator.view.chapter06;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.example.administrator.view.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/11.
 */
public class MatrixOneActivity extends Activity {


    @Bind(R.id.imageView_matrix)
    ImageView imageViewMatrix;
    @Bind(R.id.sure_btn_matrix)
    Button sureBtnMatrix;
    @Bind(R.id.reset_btn_matrix)
    Button resetBtnMatrix;
    @Bind(R.id.gridlayout_matrix)
    GridLayout gridlayoutMatrix;
    @Bind(R.id.set_btn_matrix)
    Button setBtnMatrix;
    private Bitmap bitmap;
    private float[] matrixArray = new float[9];
    private EditText[] matrixEtArray = new EditText[9];
    private int etWidth, etHeight;
    private static final String TAG = "MatrixOneActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter06_matrix_one_layout);
        ButterKnife.bind(this);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        imageViewMatrix.setImageBitmap(bitmap);
        gridlayoutMatrix.setColumnCount(3);
        gridlayoutMatrix.setRowCount(3);
        gridlayoutMatrix.post(new Runnable() {
            @Override
            public void run() {
                etWidth = gridlayoutMatrix.getMeasuredWidth() / 3;
                etHeight = gridlayoutMatrix.getMeasuredHeight() / 3;
                for (int i = 0; i < 9; i++) {
                    EditText et = new EditText(MatrixOneActivity.this);
                    et.setHeight(etHeight);
                    et.setWidth(etWidth);
                    matrixEtArray[i] = et;
                    if (i % 4 == 0) {
                        et.setText(String.valueOf(1));
                    } else {
                        et.setText(String.valueOf(0));
                    }
                    gridlayoutMatrix.addView(et);
                }
            }
        });
    }

    @OnClick({R.id.sure_btn_matrix, R.id.reset_btn_matrix,R.id.set_btn_matrix})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sure_btn_matrix:
                Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bmp);
                for (int i = 0; i < 9; i++) {
                    matrixArray[i] = Float.valueOf(matrixEtArray[i].getText().toString());
                }
                Matrix matrix = new Matrix();
                matrix.setValues(matrixArray);
                canvas.drawBitmap(bitmap, matrix, null);
                imageViewMatrix.setImageBitmap(bmp);
                break;
            case R.id.reset_btn_matrix:
                Bitmap bmp2 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas2 = new Canvas(bmp2);
                Matrix matrix2 = new Matrix();
                matrix2.setRotate(45f);
                matrix2.postTranslate(100,50);
                canvas2.drawBitmap(bitmap, matrix2, null);
                imageViewMatrix.setImageBitmap(bmp2);
                break;
            case R.id.set_btn_matrix:
                Bitmap bmp3 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas3 = new Canvas(bmp3);
                Matrix matrix3 = new Matrix();
                matrix3.setRotate(45f);
                matrix3.preTranslate(100, 50);
                canvas3.drawBitmap(bitmap, matrix3, null);
                imageViewMatrix.setImageBitmap(bmp3);
                break;
        }
    }

}
