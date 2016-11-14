package com.example.administrator.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.view.chapter06.ColorMatrixActivity;
import com.example.administrator.view.chapter06.ColorMatrixTwoActivity;
import com.example.administrator.view.chapter06.MatrixOneActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button color_matrix_btn;
    private Button color_matrix_two_btn;
    private Button matrix_one_btn;
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
        }
    }
}
