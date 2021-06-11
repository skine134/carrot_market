package com.skott.softsquared.outsourcing_simulation.src.util.adapters

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.skott.softsquared.outsourcing_simulation.src.util.custom_views.RecyclerMessageView

abstract class BaseRecyclerMessageViewAdapter<T,VH : RecyclerView.ViewHolder>(val context: Context, val arrayList:ArrayList<T>) :RecyclerView.Adapter<VH>() {
    override fun getItemCount() = arrayList.size
    override fun getItemViewType(position: Int): Int {
        return position
    }
}