package com.skott.softsquared.outsourcing_simulation.src.main.app_setting

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AppSettingViewHolder(context: Context, itemView: TextView):RecyclerView.ViewHolder(itemView){
    private val view=itemView
    fun bind(title:String)
    {
        view.text = title
    }

}