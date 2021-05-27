package com.skott.softsquared.outsourcing_simulation.src.main.home_my_carrot

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.databinding.MyCarrotRecyclerViewAdapterBinding

class MyCarrotRecyclerViewHolder(binding: MyCarrotRecyclerViewAdapterBinding) :RecyclerView.ViewHolder(binding.root){
    private lateinit var image: ImageView
    private lateinit var text:TextView
    fun bind(imageView:ImageView,textView: TextView)
    {
        this.image=imageView
        this.text=textView
    }
}