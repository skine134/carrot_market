package com.skott.softsquared.outsourcing_simulation.src.util.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.src.util.custom_views.RecyclerMessageView

abstract class BaseRecyclerMessageViewAdapter<T,VH : RecyclerView.ViewHolder>(val context: Context, val arrayList:ArrayList<T>, val recyclerMessageView: RecyclerMessageView) :RecyclerView.Adapter<VH>() {
    override fun getItemCount() = arrayList.size
    override fun getItemViewType(position: Int): Int {
        return position
    }
    fun notifyChanged()
    {
        notifyDataSetChanged()
        if(itemCount<=0)
            recyclerMessageView.messageTextView.text = recyclerMessageView.message
        else
            recyclerMessageView.messageTextView.text = ""
    }
}