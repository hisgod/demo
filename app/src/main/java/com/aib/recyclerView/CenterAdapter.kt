package com.aib.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.aib.demo.R
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class CenterAdapter :
    BaseQuickAdapter<String, BaseViewHolder>(
        R.layout.item_center
    ) {
    override fun convert(holder: BaseViewHolder, item: String) {

    }
}