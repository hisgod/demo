package com.aib.demo

import android.graphics.drawable.ColorDrawable
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion

object GlideAdapter {
    @JvmStatic
    @BindingConversion
    fun convertColorToDrawable(color: Int): ColorDrawable {
        return ColorDrawable(color)
    }
}