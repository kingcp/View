package com.example.administrator.view.chapter09_info_security;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.view.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/18.
 */
public class AndroidSecurityActivity extends Activity {

    StringBuffer sb = new StringBuffer();
    @Bind(R.id.contentTextView)
    TextView contentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter09_layout);
        ButterKnife.bind(this);
        sb.append("硬件制造商：").append(Build.MANUFACTURER).append("\n源码版本号：").append(Build.VERSION.INCREMENTAL);
        sb.append("\nSystemProperty获得os.version : " + System.getProperty("os.version"));
        contentTextView.setText(sb.toString());
    }
}
