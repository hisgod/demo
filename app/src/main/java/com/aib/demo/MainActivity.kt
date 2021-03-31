package com.aib.demo

import android.graphics.Color
import android.graphics.PixelFormat
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.aib.demo.databinding.ActivityMainBinding
import com.blankj.utilcode.util.ToastUtils

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        binding.view = this

        ToastUtils.getDefaultMaker().show(ImageView(this).apply {
            setImageResource(R.mipmap.ic_launcher)
        })
    }

    fun show() {
        Toast.makeText(this, "测试", Toast.LENGTH_SHORT).show()
    }
}
