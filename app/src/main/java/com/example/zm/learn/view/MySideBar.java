package com.example.zm.learn.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zm on 2016/12/26.
 */

public class MySideBar  extends View {

    private OnTouchingLetterChangedListener touchListener;
    // 26个字母
    public static String[] b = { "#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z" };

    private boolean showBkg = false;
    int choose = -1;
    int scrollChoose = -1;
    Paint paint = new Paint();
    Paint rectPaint = new Paint();
    float rectWidth = 0f;

    public MySideBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MySideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MySideBar(Context context) {
        super(context);
        init();
    }

    private void init() {
        rectPaint.setColor(Color.parseColor("#CCCCCC"));
        rectWidth = paint.measureText("#");
    }

    /**
     * 重写这个方法
     */
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (showBkg) {
            canvas.drawColor(Color.parseColor("#CCCCCC"));
        }
        final int height = getHeight();
        final int width = getWidth();
        final int singleHeight = height / b.length;
        final float xRectPos = ((float) width - paint.measureText("#")) / 2.0f - rectWidth;
        final float xRectPos2 = xRectPos + 3.0f * rectWidth;
        for (int i = 0; i < b.length; i++) {
            paint.setFakeBoldText(true);
            paint.setAntiAlias(true);
            final float xPos = ((float) width - paint.measureText(b[i])) / 2.0f;
            final float yPos = singleHeight * i + singleHeight;
            if (i == choose) {
                paint.setColor(Color.RED);
                canvas.drawRect(xRectPos, yPos - singleHeight / 2.0f, xRectPos2, yPos + rectWidth, rectPaint);
            }
            canvas.drawText(b[i], xPos, yPos, paint);
            paint.reset();
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        final float y = event.getY();
        final int c = (int) (y / getHeight() * b.length);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                showBkg = true;
                if (choose != c && touchListener != null) {
                    doOnActionDown(c);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (choose != c && touchListener != null) {
                    doOnActionDown(c);
                }
                break;
            case MotionEvent.ACTION_UP:
                showBkg = false;
                invalidate();
                break;
        }
        return true;
    }

    /**
     * listview滚动时候调用它
     *
     * @param c
     */
    public void setColorWhenListViewScrolling(int c) {
        if (scrollChoose != c) {
            scrollChoose = c;
            String string = ListContantsUtil.AbcList.get(c);
            for (int i = c; i < b.length; ++i) {
                if (string.equals(b[i])) {
                    choose = i;
                    invalidate();
                    break;
                }
            }
        }
    }

    /**
     * 当侧边栏被按下的动作
     * @param c
     */
    private void doOnActionDown(int c) {
        if (c > 0 && c < b.length) {
            if (ListContantsUtil.indexPositionMap.containsKey(b[c])) {
                touchListener.onTouchingLetterChanged(b[c]);
                choose = c;
                invalidate();
            } else {
                c = c - 1;
                doOnActionDown(c);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public void setOnTouchingLetterChangedListener(OnTouchingLetterChangedListener touchListener) {
        this.touchListener = touchListener;
    }

    /**
     * 用来通知activity显示选中的字母
     * @author freeson
     *
     */
    public interface OnTouchingLetterChangedListener {
        public void onTouchingLetterChanged(String s);
    }

}