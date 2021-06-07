package com.aib.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.BindingMethod;
import androidx.databinding.BindingMethods;

import com.aib.demo.R;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ScreenUtils;

import org.jetbrains.annotations.NotNull;

public class ExpandTextView extends View {

    //文本画笔
    private Paint paint;
    //文本
    private String text;
    //文本大小
    private int textSize;
    private float lineHeight;
    private boolean isExpand = false;
    //默认最多绘制几行
    private int maxLine = 3;
    private Bitmap startBiamp, endBitmap;
    private int line;
    //行距
    private int lineSpacing;
    private float offY;

    public ExpandTextView(@NonNull @NotNull Context context) {
        super(context);
    }

    public ExpandTextView(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandTextView);
        text = typedArray.getString(R.styleable.ExpandTextView_text);
        textSize = typedArray.getDimensionPixelSize(R.styleable.ExpandTextView_textSize, 28);
        Drawable startDrawable = typedArray.getDrawable(R.styleable.ExpandTextView_startImage);
        startBiamp = ImageUtils.drawable2Bitmap(startDrawable);
        Drawable endDrawable = typedArray.getDrawable(R.styleable.ExpandTextView_endImage);
        endBitmap = ImageUtils.drawable2Bitmap(endDrawable);
        lineSpacing = typedArray.getDimensionPixelSize(R.styleable.ExpandTextView_lineSpacing, 0);
        typedArray.recycle();

        paint = new Paint();
        paint.setTextSize(textSize);
        Paint.FontMetrics metrics = paint.getFontMetrics();
        lineHeight = (metrics.descent - metrics.ascent);
        offY = metrics.bottom - metrics.leading;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (text == null) {
            setMeasuredDimension(0, 0);
            return;
        }

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = 0, height = 0;

        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) getLayoutParams();

        width = ScreenUtils.getScreenWidth() - params.leftMargin - params.rightMargin;

        line = measureTextLine(text);
        if (line >= maxLine && !isExpand) {//如果行数大于3行，就只绘制3行
            setMeasuredDimension(width, (int) ((lineHeight) * maxLine));
        } else {//如果行数小于3行，就直接按计算出来的行数绘制
            setMeasuredDimension(width, (int) ((lineHeight) * line));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (text == null)
            return;

        int start = 0;
        int end = 0;
        int index = 0;
        int measureWidth = getWidth();

        while (start < text.length()) {
            float drawY = lineHeight + lineHeight  * index;

            if (index == 2 && !isExpand && line > maxLine) {//如果是第3行，且没有展开，文本绘制宽度需要减去指示器宽度，并且绘制指示器
                measureWidth -= startBiamp.getWidth();

                //绘制指示器
                canvas.drawBitmap(startBiamp, measureWidth, drawY - startBiamp.getHeight(), paint);
            }

            if (index == measureTextLine(text) - 1 && isExpand && line > maxLine) {//如果是最后1行，且展开了，文本绘制宽度需要减去指示器宽度，并且绘制指示器
                measureWidth -= endBitmap.getWidth();

                //绘制指示器
                canvas.drawBitmap(endBitmap, measureWidth, drawY - endBitmap.getHeight(), paint);
            }

            int breakText = paint.breakText(text, 0, text.length(), true, measureWidth, new float[]{});

            end += breakText;
            if (end > text.length()) {//如果end的值大于文本长度，就按文本长度
                end = text.length();
            }

            canvas.drawText(text, start, end, 0, drawY, paint);

            start += breakText;
            index++;
        }
    }

    private int measureTextLine(String text) {

        int start = 0;
        int line = 0;
        while (start < text.length()) {
            int breakText = paint.breakText(text, 0, text.length(), true, ScreenUtils.getScreenWidth(), new float[]{});
            start += breakText;
            line++;
        }

        LogUtils.e("可绘制：" + line + "行");
        return line;
    }

//    @BindingAdapter(value = {"text"})
//    public static void showText(ExpandTextView view, String text) {
//        view.setText(text);
//    }

    public void setText(String text) {
        this.text = text;
        invalidate();
        requestLayout();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if (isExpand) {//展开
                    isExpand = false;
                } else {//折叠
                    //执行展开逻辑
                    isExpand = true;
                }
                invalidate();
                requestLayout();
                break;
        }
        return super.onTouchEvent(event);
    }
}
