package com.aib.view.shador;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.aib.demo.R;

public class BitmapShadorView extends View {

    private final BitmapShader bitmapShader;
    private final Paint paint;
    private final Bitmap bitmap;
    private Matrix matrix;
    private int viewSize;
    private final int bitmapWidth;
    private final int bitmapHeight;

    public BitmapShadorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test1);
        bitmapWidth = bitmap.getWidth();
        bitmapHeight = bitmap.getHeight();
        bitmapShader = new BitmapShader(bitmap, TileMode.REPEAT, TileMode.REPEAT);
        paint.setShader(bitmapShader);
        matrix = new Matrix();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        viewSize = Math.min(width, height);

        setMeasuredDimension(viewSize, viewSize);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        //获取缩放还是放大值
//        float scale = (float) viewSize / Math.min(bitmapHeight, bitmapWidth);
        float scale = 0.5f;
        matrix.setScale(scale, scale);
        //平移至图片中心
        float dx = (viewSize - bitmapWidth * scale) * 0.5f;
        float dy = (viewSize - bitmapHeight * scale) * 0.5f;
        matrix.postTranslate(dx, dy);

        bitmapShader.setLocalMatrix(matrix);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画圆
        float radius = viewSize / 2f;
        canvas.drawCircle(radius, radius, radius, paint);
    }
}
