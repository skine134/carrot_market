package com.skott.softsquared.outsourcing_simulation.src.main.collect_user

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.databinding.CollectUserAdapterBinding
import com.skott.softsquared.outsourcing_simulation.src.main.collect_user.model.CollectUserResponse


class CollectUserAdapter(val context: Context,val arrayList:ArrayList<CollectUserResponse>) :RecyclerView.Adapter<CollectUserViewHolder>(){
    private lateinit var binding: CollectUserAdapterBinding
    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectUserViewHolder {
        binding = CollectUserAdapterBinding.inflate(inflater,parent,false)
        return CollectUserViewHolder(context,binding)
    }

    override fun onBindViewHolder(holder: CollectUserViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    override fun getItemCount()=arrayList.size

    override fun getItemViewType(position: Int): Int {
        return position
    }
}