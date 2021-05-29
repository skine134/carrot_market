package com.skott.softsquared.outsourcing_simulation.src.util.custom_views

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.databinding.ListDialogViewBinding

class ListDialogView(context: Context, attrs: AttributeSet) :
    BaseCustomView<ListDialogViewBinding>(ListDialogViewBinding::inflate, context, attrs) {

    fun getRecyclerView(): RecyclerView {
        return binding.list
    }

    fun <T : RecyclerView.ViewHolder> setItemList(
        adapter: RecyclerView.Adapter<T>, layoutManager: LinearLayoutManager,
        event: (() -> Unit?)? = null
    ) {
        binding.list.layoutManager = layoutManager
        binding.list.adapter = adapter
//        if (event!=null)
//            binding.list.adapter.
    }

}