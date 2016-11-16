package com.example.administrator.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.administrator.view.chapter06.BitmapMeshActivity;
import com.example.administrator.view.chapter06.BitmapShaderActivity;
import com.example.administrator.view.chapter06.ColorMatrixActivity;
import com.example.administrator.view.chapter06.ColorMatrixTwoActivity;
import com.example.administrator.view.chapter06.MatrixOneActivity;
import com.example.administrator.view.chapter06.PorterDuffXfermodeActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button color_matrix_btn;
    private Button color_matrix_two_btn;
    private Button matrix_one_btn;
    private Button bitmap_mesh_btn;
    private Button PosterDuffXfermode_btn;
    private Button Share_btn;
    private boolean flag = false;
    private static final String TAG = "MainActivity";
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            for(int i = 0 ; i < 3 ; i++){
               System.out.println(TAG + " handleMessage");
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        color_matrix_btn = (Button) findViewById(R.id.color_matrix_btn);
        color_matrix_btn.setOnClickListener(this);
        color_matrix_two_btn = (Button) findViewById(R.id.color_matrix_two_btn);
        color_matrix_two_btn.setOnClickListener(this);
        matrix_one_btn = (Button) findViewById(R.id.matrix_one_btn);
        matrix_one_btn.setOnClickListener(this);
        bitmap_mesh_btn = (Button) findViewById(R.id.bitmap_mesh_btn);
        bitmap_mesh_btn.setOnClickListener(this);
        PosterDuffXfermode_btn = (Button) findViewById(R.id.PosterDuffXfermode_btn);
        PosterDuffXfermode_btn.setOnClickListener(this);
        Share_btn = (Button) findViewById(R.id.Share_btn);
        Share_btn.setOnClickListener(this);
        if(flag){
            Log.d(TAG, "onCreate: ");
            System.out.println(TAG + " flag = true");
        }else{
            handler.sendEmptyMessage(1);
            System.out.println(TAG + " flag = false");
        }
        System.out.println(TAG + " finish");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.color_matrix_btn:
                Intent intent = new Intent(MainActivity.this, ColorMatrixActivity.class);
                startActivity(intent);
            break;
            case R.id.color_matrix_two_btn:
                Intent intent2 = new Intent(MainActivity.this, ColorMatrixTwoActivity.class);
                startActivity(intent2);
                break;
            case R.id.matrix_one_btn:
                Intent intent3 = new Intent(MainActivity.this, MatrixOneActivity.class);
                startActivity(intent3);
                break;
            case R.id.bitmap_mesh_btn:
                Intent intent4 = new Intent(MainActivity.this, BitmapMeshActivity.class);
                startActivity(intent4);
                break;
            case R.id.PosterDuffXfermode_btn:
                Intent intent5 = new Intent(MainActivity.this, PorterDuffXfermodeActivity.class);
                startActivity(intent5);
                break;
            case R.id.Share_btn:
                Intent intent6 = new Intent(MainActivity.this, BitmapShaderActivity.class);
                startActivity(intent6);
                break;
        }
    }
}
