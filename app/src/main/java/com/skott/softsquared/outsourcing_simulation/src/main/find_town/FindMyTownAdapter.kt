package com.skott.softsquared.outsourcing_simulation.src.main.find_town

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.skott.softsquared.outsourcing_simulation.databinding.FindTownNearByTownAdapterBinding
import com.skott.softsquared.outsourcing_simulation.src.main.find_town.model.TownInfo
import com.skott.softsquared.outsourcing_simulation.src.util.adapters.BaseRecyclerMessageViewAdapter
import com.skott.softsquared.outsourcing_simulation.src.util.custom_views.RecyclerMessageView

class FindMyTownAdapter(context: Context,arrayList: ArrayList<TownInfo>,recyclerMessageView: RecyclerMessageView):BaseRecyclerMessageViewAdapter<TownInfo,FindMyTownViewHolder>(context,arrayList,recyclerMessageView) {
    private val inflater=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding: FindTownNearByTownAdapterBinding
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): FindMyTownViewHolder {
        binding = FindTownNearByTownAdapterBinding.inflate(inflater,parent,false)
        return FindMyTownViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FindMyTownViewHolder, position: Int) {
        holder.bind(arrayList[position].address,arrayList[position].relatedTown?:"")
    }


}