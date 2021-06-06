package com.aib.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.aib.demo.R;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ScreenUtils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ExpandTextView extends View {

    //文本画笔
    private Paint paint;
    //文本
    private String text;
    //文本大小
    private int textSize;
    //一行高度
    private float lineHeight;
    //绘制文本Y坐标（基线）
    private float drawTextY;

    private List<String> line = new ArrayList<>();

    public ExpandTextView(@NonNull @NotNull Context context) {
        super(context);
    }

    public ExpandTextView(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandTextView);
        text = typedArray.getString(R.styleable.ExpandTextView_text);
        textSize = typedArray.getDimensionPixelSize(R.styleable.ExpandTextView_textSize, 28);
        typedArray.recycle();

        paint = new Paint();
        paint.setTextSize(textSize);
        Paint.FontMetrics metrics = paint.getFontMetrics();
        lineHeight = metrics.bottom - metrics.top;
        drawTextY = paint.descent() - paint.ascent();


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = 0, height = 0;

//        if (widthMode == MeasureSpec.EXACTLY) {
//            width = 200;
//        }

        setMeasuredDimension(ScreenUtils.getScreenWidth(), (int) lineHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //一行可容纳字数
        int breakText = paint.breakText(text, 0, text.length(), true, getWidth(), new float[]{});
        LogUtils.e("一行容纳：" + breakText + "字");

        int start = 0;
        int end = breakText;
        String substring = text.substring(start, end);
        boolean isAdd = line.add(substring);
        for (; ; ) {

        }

//        canvas.drawText(text, 0, text.length(), 0, drawTextY, paint);
    }
}
