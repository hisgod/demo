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

import org.jetbrains.annotations.NotNull;

public class ExpandTextView extends AppCompatTextView {

    private Bitmap bitmap;
    private float[] widths = new float[0];
    private TextPaint paint;
    private int breakText;
    private int line = 3;

    //是否展开
    private boolean isExpand = false;

    public ExpandTextView(@NonNull @NotNull Context context) {
        super(context);
    }

    public ExpandTextView(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
        downAnimation();

        //获取画笔
        paint = getPaint();

        //注意此处showText后+ " "主要是为了占位
//        SpannableString ss = new SpannableString(getText());
//        int len = ss.length();
//        //图片
//        Drawable d = ContextCompat.getDrawable(context, (R.mipmap.ic_down));
//        d.setBounds(10, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
//        //构建ImageSpan
//        ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
//        ss.setSpan(span, len - 1, len, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
//        setText(ss);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //折叠时候字数长度
        int foldNum = 0;
        //提示Icoon区域的宽
        float tipWidth = getWidth() - bitmap.getWidth() * 2;
        //提示Iocn区域的高
        float tipHeight = getHeight() - (Math.max(measureTextHeight(paint), bitmap.getHeight()) - Math.min(measureTextHeight(paint), bitmap.getHeight()));

        LogUtils.e("总字数：" + getText().length());

        int end;
        int start = 0;
        for (int i = 0; i < getLineCount(); i++) {
            end = getLayout().getLineEnd(i);
            CharSequence lineText = getText().subSequence(start, end); //指定行的内容
            start = end;

            if (i == getLineCount() - 1) {//如果是最后一行
                if (lineText.length() == 0) {
                    return;
                }
                breakText = paint.breakText(lineText, 0, lineText.length(), true, tipWidth, widths);
                /*if (!isExpand) {
                    lineText = lineText.subSequence(0, breakText - 1);
                    lineText += "...";
                }*/
            } else {//不是最后一行，按照每行的宽度
                breakText = lineText.length();
            }

            foldNum += breakText;
            LogUtils.e("每行字数：" + breakText);
            //绘制每一行的文本
            canvas.drawText(lineText, 0, breakText, 0, measureTextHeight(paint) + getLineHeight() * i , paint);
        }

        //绘制提示Icon
        if (foldNum == getText().length() && getLineCount() <= line)
            return;
        canvas.drawBitmap(bitmap, tipWidth, tipHeight, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                int maxLines = getMaxLines();
                if (maxLines == Integer.MAX_VALUE) {//折叠
                    isExpand = false;
                    downAnimation();
                    setMaxLines(line);
                } else {//展开
                    isExpand = true;
                    upAnimation();
                    setMaxLines(Integer.MAX_VALUE);
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

    /**
     * 测量文字的高度
     */
    public float measureTextHeight(Paint paint) {
        float height = 0;
        if (null == paint) {
            return height;
        }
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        height = fontMetrics.descent - fontMetrics.ascent;
        return height;
    }
}
