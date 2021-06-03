package com.aib.recyclerView

import android.content.Context
import android.graphics.Canvas
import android.graphics.PointF
import android.graphics.RectF
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.widget.Button
import android.widget.Toast
import androidx.core.view.forEach
import androidx.recyclerview.widget.RecyclerView
import com.aib.demo.R
import com.blankj.utilcode.util.ToastUtils

class MyView(context: Context, attributeSet: AttributeSet) : RecyclerView(context, attributeSet) {
    private var view: View? = null

    fun setCustomView(res: Int) {
        view = LayoutInflater.from(context).inflate(res, this, false).apply {
            findViewById<Button>(R.id.btn).setOnClickListener {
                ToastUtils.showShort("哈哈")
            }
        }
        requestLayout()
    }

    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
        super.onMeasure(widthSpec, heightSpec)

        measureChild(view, widthSpec, heightSpec)
    }

    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)
        drawChild(canvas, view, drawingTime)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)

        view?.let {
            it.layout(
                measuredWidth - it.measuredWidth,
                (measuredHeight - it.measuredHeight) / 2,
                measuredWidth,
                (measuredHeight - it.measuredHeight) / 2 + it.measuredHeight
            )
        }
    }

//    override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {
//        when (e?.action) {
//            MotionEvent.ACTION_DOWN -> {
//
//            }
//            MotionEvent.ACTION_MOVE -> {
//
//            }
//            MotionEvent.ACTION_UP -> {
//                view?.let {
//                    RectF(
//                        it.left.toFloat(),
//                        it.top.toFloat(),
//                        it.right.toFloat(),
//                        it.bottom.toFloat()
//                    ).apply {
//                        if (contains(e.x, e.y)) {
//                            view?.onTouchEvent(e)
//                            return true
//                        } else {
//                            return super.onInterceptTouchEvent(e)
//                        }
//                    }
//                }
//            }
//        }
//        return super.onInterceptTouchEvent(e)
//    }

    override fun dispatchTouchEvent(e: MotionEvent?): Boolean {
        when (e?.action) {
            MotionEvent.ACTION_DOWN -> {

            }
            MotionEvent.ACTION_MOVE -> {

            }
            MotionEvent.ACTION_UP -> {
                view?.let {
                    RectF(
                        it.left.toFloat(),
                        it.top.toFloat(),
                        it.right.toFloat(),
                        it.bottom.toFloat()
                    ).apply {
                        if (contains(e.x, e.y)) {
                            ToastUtils.showShort("你妹的")
                            return true
                        } else {
                            return super.dispatchTouchEvent(e)
                        }
                    }
                }
            }
        }
        return super.dispatchTouchEvent(e)
    }

    override fun onTouchEvent(e: MotionEvent?): Boolean {
        var touchX = 0f
        var touchY = 0f
        when (e?.action) {
            MotionEvent.ACTION_DOWN -> {
                touchX = x
                touchY = y
            }
            MotionEvent.ACTION_MOVE -> {

            }
            MotionEvent.ACTION_UP -> {
                view?.let {
                    RectF(
                        it.left.toFloat(),
                        it.top.toFloat(),
                        it.right.toFloat(),
                        it.bottom.toFloat()
                    ).apply {
                        if (contains(e.x, e.y)) {
                            performClick()
                            return true
                        } else {
                            return super.onTouchEvent(e)
                        }
                    }
                }
            }
        }
        return super.onTouchEvent(e)
    }
}