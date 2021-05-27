package com.skott.softsquared.outsourcing_simulation.src.main.home_my_carrot

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.databinding.MyCarrotRecyclerViewAdapterBinding
import com.skott.softsquared.outsourcing_simulation.src.main.home_my_carrot.model.MyCarrotListItem

class MyCarrotRecyclerAdapter(context: Context, val arrayList: ArrayList<MyCarrotListItem>) :RecyclerView.Adapter<MyCarrotRecyclerViewHolder>(){
    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding:MyCarrotRecyclerViewAdapterBinding
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MyCarrotRecyclerViewHolder {
        binding = MyCarrotRecyclerViewAdapterBinding.inflate(inflater,parent,false)
        return MyCarrotRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyCarrotRecyclerViewHolder, position: Int) {
        binding.itemImageView.setImageResource(arrayList[position].imageId)
        binding.itemContentTextView.text = arrayList[position].name
        holder.bind(binding.itemImageView,binding.itemContentTextView)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
    override fun getItemCount()=arrayList.count()
}