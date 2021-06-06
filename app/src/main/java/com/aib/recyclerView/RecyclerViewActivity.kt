package com.aib.recyclerView

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aib.demo.R
import com.blankj.utilcode.util.BarUtils
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        BarUtils.setStatusBarVisibility(this, false)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        tv.setOnClickListener {

        }
    }
}