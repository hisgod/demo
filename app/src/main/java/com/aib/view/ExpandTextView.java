package com.aib.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.aib.demo.R;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SpanUtils;

import org.jetbrains.annotations.NotNull;

public class ExpandTextView extends AppCompatTextView {

    private Bitmap bitmap;
    private float[] widths = new float[0];
    private TextPaint paint;
    private int breakText;
    private int defaultLine = 3;
    private int currentLine = 0;
    private boolean isCalculation = false;
    private Paint tipPaint;

    public ExpandTextView(@NonNull @NotNull Context context) {
        super(context);
    }

    public ExpandTextView(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
        downAnimation();

        //文本画笔
        paint = getPaint();
        paint.setColor(getTextColors().getDefaultColor());
        paint.setAntiAlias(true);

        //指示器画笔
        tipPaint = new Paint();

        setMaxLines(defaultLine);
//        setMaxLines(1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getLineCount() <= defaultLine) {//如果小于等于3行，直接绘制
            super.onDraw(canvas);
        } else {//如果大于3行，自定义绘制方式

            float tipWidth = getWidth() - bitmap.getWidth() - getPaddingRight();
            float tipHeight = getHeight() - bitmap.getHeight() / 2 - getLineHeight() / 2;

            if (currentLine == 0) {
                currentLine = getLineCount();
            }

            int end;
            int start = 0;
            for (int i = 0; i < getLineCount(); i++) {
                end = getLayout().getLineEnd(i);
                CharSequence lineText = getText().subSequence(start, end); //指定行的内容
                start = end;

                if (i == 2) {//第3行
                    if (getMaxLines() == defaultLine) {//折叠，显示指示器
                        breakText = paint.breakText(lineText, 0, lineText.length(), true, tipWidth, widths);
                    } else {  //展开，显示正常文本
                        breakText = lineText.length();
                    }
                } else if (i == getLineCount() - 1) {//最后一行
                    float measureText = paint.measureText(lineText.toString());
                    if (measureText == getWidth()) {//显示全部内容，并且需要增加多一行显示指示器
                        breakText = lineText.length();
                        if (!isCalculation) {//保证只被执行1次
                            currentLine += 1;
                            isCalculation = true;
                        }
                    } else {//显示部分内容+指示器
                        breakText = paint.breakText(lineText, 0, lineText.length(), true, tipWidth, widths);
                    }
                } else {
                    breakText = lineText.length();
                }

                //绘制每一行的文本
                canvas.drawText(lineText, 0, breakText, getPaddingLeft(), getLineHeight() - 5 + getLineHeight() * i, paint);
            }

            canvas.drawBitmap(bitmap, tipWidth, tipHeight, tipPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                int maxLines = getMaxLines();
                if (getLineCount() <= defaultLine) {//<=3行
                    if (maxLines == defaultLine) {
                        upAnimation();
                        setMaxLines(Integer.MAX_VALUE);
                    } else {
                        downAnimation();
                        setMaxLines(defaultLine);
                    }
                } else {//大于3行
                    if (maxLines == defaultLine) {
                        upAnimation();
                        setLines(currentLine);
                    } else {
                        downAnimation();
                        setLines(defaultLine);
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    //加载箭头向上的Icon
    private void upAnimation() {
        bitmap = ImageUtils.getBitmap(R.mipmap.ic_up);
    }

    //加载箭头向下的Icon
    private void downAnimation() {
        bitmap = ImageUtils.getBitmap(R.mipmap.ic_down);
    }
}
