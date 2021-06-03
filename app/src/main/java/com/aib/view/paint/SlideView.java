package com.aib.view.paint;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ScreenUtils;

public class SlideView extends AppCompatTextView {
    private Scroller mScroller;
    private int screenWidth;
    private int scrollX;
    private int scrollY;

    public SlideView(Context context) {
        this(context, null);
    }

    public SlideView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mScroller = new Scroller(context);

        screenWidth = ScreenUtils.getScreenWidth();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        // 判断Scroller是否执行完毕
        if (mScroller.computeScrollOffset()) {
            ((View) getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            // 通过重绘来不断调用computeScroll
            invalidate();
        }
    }

    private int mLastX;
    private int mLastY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = (int) event.getX();
                mLastY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) event.getX();
                int moveY = (int) event.getY();
                //实现View跟随手指移动的效果
                ((View) getParent()).scrollBy(scrollX + mLastX - moveX, scrollY + mLastY - moveY);
                break;
            case MotionEvent.ACTION_UP:
//                //当手指抬起时执行滑动过程
//                View view = (View) getParent();
//                int scrollX = view.getScrollX();
//                int scrollY = view.getScrollY();
//                LogUtils.e("scrollX" + scrollX);
//                LogUtils.e("scrollY" + scrollY);
//                if (Math.abs(scrollX) >= screenWidth) {
//                    scrollX = -screenWidth;
//                }
//                mScroller.startScroll(scrollX, scrollY, scrollX, scrollY);
//                //调用重绘来间接调用computeScroll()方法
//                invalidate();

                scrollX = getScrollX();
                scrollY = getScrollY();
                break;
        }
        return true;
    }
}
