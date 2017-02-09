package com.example.zm.learn.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

import com.example.zm.learn.R;
import com.example.zm.learn.base.BaseActivity;

public class Activity05 extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_05);
        initToolBar(getIntent().getStringExtra("string"),true);
        ViewFlipper vf = (ViewFlipper) findViewById(R.id.vf);
        vf.addView(View.inflate(this, R.layout.a5_v1, null));
        vf.addView(View.inflate(this, R.layout.a5_v2, null));
        vf.addView(View.inflate(this, R.layout.a5_v3, null));
    }
}
