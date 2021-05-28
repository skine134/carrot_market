package com.skott.softsquared.outsourcing_simulation.src.main.find_town

import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.databinding.FindTownNearByTownAdapterBinding

class FindMyTownViewHolder(binding: FindTownNearByTownAdapterBinding): RecyclerView.ViewHolder(binding.root) {
    private var address=binding.addressTextView
    private var relatedTown=binding.relatedTownTextView
    fun bind(address:String,relatedTown:String="")
    {
        this.address.text=address
        this.relatedTown.text=relatedTown
    }
}