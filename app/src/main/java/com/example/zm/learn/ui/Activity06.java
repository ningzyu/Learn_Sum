package com.example.zm.learn.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.zm.learn.R;

public class Activity06 extends AppCompatActivity {
    private ViewFlipper flipper;
    //X横坐标
    private float currentX;
    //背景图片int[] id
    private int[] resID = {R.drawable.h1, R.drawable.h2, R.drawable.h4, R.drawable.h5};
    private TranslateAnimation leftInAnim,leftOutAnim,rightInAnim,rightOutAnim;//代码设置动画
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_06);
        /**
         * setInAnimation      设置View进入屏幕时候使用的动画
           setOutAnimation     设置View退出屏幕时候使用的动画
           showPrevious        显示ViewFlipper里面的上一个View
           showNext            显示ViewFlipper里面的下一个View
           setFlipInterval     设置View之间切换的时间间隔
           startFlipping       使用setFlipInterval方法设置的时间间隔来开始切换所有的View,切换会循环进行
           stopFlipping        停止View切换
           isFlipping          用来判断View切换是否正在进行
           setDisplayedChild   切换到指定子View
         */
        flipper = (ViewFlipper) findViewById(R.id.view_flipper);
        Trans();
        //动态导入的方式为ViewFlipper加入子View//添加要滚动的View
        for (int i = 0; i < resID.length; i++) {
            flipper.addView(getimageview(resID[i]));
        }
        //设置开始和结束动画
        flipper.setInAnimation(this,R.anim.left_in);
        flipper.setOutAnimation(this,R.anim.left_out);
        //设置间隔时间
        flipper.setFlipInterval(3000);
        //动画的监听
        flipper.getInAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //动画开始时
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                //动画结束时
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
                //重复
            }
        });
        //开始轮播
        flipper.startFlipping();
    }

    private ImageView getimageview(int resID) {
        ImageView image = new ImageView(this);
        image.setBackgroundResource(resID);
        return image;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                flipper.stopFlipping();// 当手指按下时，停止图片的循环播放。并记录当前x坐标
                currentX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                if (event.getX() - currentX > 100) { // 手指向右滑动，左侧滑入，右侧滑出
                    flipper.setInAnimation(leftInAnim);
                    flipper.setOutAnimation(rightOutAnim);
                    flipper.showPrevious();
//                    flipper.startFlipping();
                } else if (currentX - event.getX() > 100) {// 手指向左滑动，右侧滑入，左侧滑出
                    flipper.setInAnimation(rightInAnim);
                    flipper.setOutAnimation(leftOutAnim);
                    flipper.showNext();
//                    flipper.startFlipping();
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    //代码设置动画
    public void Trans(){
        // 图片从右侧滑入
        rightInAnim = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        rightInAnim.setDuration(1000);

        // 图片从左侧滑出
        leftOutAnim = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        leftOutAnim.setDuration(1000);

        // 图片从右侧滑出
        rightOutAnim = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        rightOutAnim.setDuration(1000);

        // 图片从左侧滑入
        leftInAnim = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        leftInAnim.setDuration(1000);


    }
}