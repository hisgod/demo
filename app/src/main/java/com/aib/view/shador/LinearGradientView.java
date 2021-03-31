package com.aib.view.shador;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.text.TextPaint;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class LinearGradientView extends AppCompatTextView {

    private  LinearGradient gradient;
    private int[] colors = {Color.RED, Color.BLUE};
    private  TextPaint paint;

    public LinearGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = getPaint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        CharSequence text = getText();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        gradient = new LinearGradient(0, 300, 300, 300, colors, null, Shader.TileMode.CLAMP);
        paint.setShader(gradient);
    }
}
