package com.aib.recyclerView

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.aib.demo.R
import com.aib.demo.databinding.ActivityRecyclerViewBinding
import com.aib.view.Msg
import com.blankj.utilcode.util.BarUtils
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        BarUtils.setStatusBarVisibility(this, false)
        super.onCreate(savedInstanceState)
        val contentView = DataBindingUtil.setContentView<ActivityRecyclerViewBinding>(
            this,
            R.layout.activity_recycler_view
        )
        contentView.data =
            Msg("话说天下大势，分久必合，合久必分。周末七国分争，并入于秦。及秦灭之后，楚、汉分争，又并入于汉。汉朝自高祖斩白蛇而起义，一统天下，后来光武中兴，传至献帝，遂分为三国。")
//            Msg("历史的车轮滚过波澜壮阔的三国时代，中原大地迎来了百年未遇的和平，人们都以为盛世即将来临，可谁都没想到")
//            Msg("历史的车轮滚过波澜壮阔的三国时代，中原大地迎来了百年未遇的和平")
//            Msg("历史的车轮滚过波澜壮阔的三国时代")

        tv.setOnClickListener {

        }
    }
}