package com.aib.view.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.aib.demo.R;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ScreenUtils;

public class PaintView extends View {

    private final Paint paint;

    private Path path;

    private int startPointX, startPointY;
    private int endPointX, endPointY;

    private float controlX;
    private float controlY;

    private final int screenWidth;
    private final int screenHeight;

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);

        screenWidth = ScreenUtils.getScreenWidth();
        screenHeight = ScreenUtils.getScreenHeight();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        //确定起点
        startPointX = 0;
        startPointY = screenHeight / 2;
        //确定终点
        endPointX = screenWidth;
        endPointY = screenHeight / 2;
        //曲线控制点默认在曲线中点
        controlX = screenWidth / 2;
        controlY = screenHeight / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        path = new Path();

        path.moveTo(startPointX, startPointY);
        path.quadTo(controlX, controlY, endPointX, endPointY);

        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                controlX = event.getX();
                controlY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                reset();
                break;
        }
        invalidate();
        return true;
    }

    private void reset() {
        controlX = screenWidth / 2;
        controlY = screenHeight / 2;
    }
}
