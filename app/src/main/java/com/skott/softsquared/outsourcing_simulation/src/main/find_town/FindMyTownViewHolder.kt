package com.skott.softsquared.outsourcing_simulation.src.main.find_town

import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.databinding.FindTownNearByTownAdapterBinding
import com.skott.softsquared.outsourcing_simulation.src.main.find_town.model.FindMyTownResponse

class FindMyTownViewHolder(binding: FindTownNearByTownAdapterBinding): RecyclerView.ViewHolder(binding.root) {
    private var address=binding.addressTextView
    fun bind(findMyTownResponse:FindMyTownResponse)
    {
        this.address.text=findMyTownResponse.village
//        관련 동네 : 개발 난이도가 높아서 버림
//        this.relatedTown.text=findMyTownResponse.
    }
}