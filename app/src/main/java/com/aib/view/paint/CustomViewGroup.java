package com.aib.view.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.BarUtils;

public class CustomViewGroup extends FrameLayout {
    public CustomViewGroup(Context context) {
        super(context);
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);

        BarUtils.transparentStatusBar((AppCompatActivity) context);
        BarUtils.setNavBarColor((AppCompatActivity) context, Color.argb(150, 0, 0, 0));
        setPadding(0, BarUtils.getStatusBarHeight(), 0, 0);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.drawARGB(150, 0, 0, 0);
    }
}
