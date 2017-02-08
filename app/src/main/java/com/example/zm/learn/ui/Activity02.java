package com.example.zm.learn.ui;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.zm.learn.R;
import com.example.zm.learn.ulit.SwipeRefreshLayout;

public class Activity02 extends AppCompatActivity implements
        SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout mSwipeLayout;
    private WebView mPage;
    private TextView mHint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_02);
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        mPage = (WebView) findViewById(R.id.page);
        mHint = (TextView) findViewById(R.id.hint);

        WebSettings webSettings = mPage.getSettings();
        webSettings.setBuiltInZoomControls(false);
        webSettings.setSupportZoom(false);
        webSettings.setJavaScriptEnabled(true);

        mPage.setWebViewClient(new WebViewClient());

        mPage.loadUrl("http://wap.qq.com");

        mSwipeLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        mHint.setText("正在刷新，请等待");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 停止刷新
                mSwipeLayout.setRefreshing(false);
                mSwipeLayout.stopRefresh();
                mHint.setText("下拉刷新");
                mPage.loadUrl("http://wap.163.com");
            }
        }, 3000); // 3秒后发送消息，停止刷新
    }

    @Override
    public void onNormal() {
        mHint.setText("下拉刷新");
    }

    @Override
    public void onLoose() {
        mHint.setText("松手刷新");
    }
}
