package com.skott.softsquared.outsourcing_simulation.src.main.home_near_by

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.skott.softsquared.outsourcing_simulation.databinding.NearByAdapterBinding
import com.skott.softsquared.outsourcing_simulation.src.main.home_near_by.model.NearbyPostModel
import com.skott.softsquared.outsourcing_simulation.src.util.adapters.BaseRecyclerMessageViewAdapter
import com.skott.softsquared.outsourcing_simulation.src.util.custom_views.RecyclerMessageView

class NearbyAdapter(
    context: Context,
    arrayList: ArrayList<NearbyPostModel>,
    recyclerMessageView: RecyclerMessageView
) : BaseRecyclerMessageViewAdapter<NearbyPostModel, NearbyViewHolder>(
    context,
    arrayList,
    recyclerMessageView
) {
    private lateinit var binding: NearByAdapterBinding
    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): NearbyViewHolder {
        binding = NearByAdapterBinding.inflate(inflater,parent,false)
        return NearbyViewHolder(context,binding)
    }

    override fun onBindViewHolder(holder: NearbyViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

}