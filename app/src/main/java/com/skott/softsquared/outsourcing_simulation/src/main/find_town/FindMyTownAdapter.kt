package com.skott.softsquared.outsourcing_simulation.src.main.find_town

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.skott.softsquared.outsourcing_simulation.databinding.FindTownNearByTownAdapterBinding
import com.skott.softsquared.outsourcing_simulation.src.main.find_town.model.FindMyTownResponse
import com.skott.softsquared.outsourcing_simulation.src.main.find_town.model.RegisterAddressRequest
import com.skott.softsquared.outsourcing_simulation.src.util.adapters.BaseRecyclerMessageViewAdapter
import com.skott.softsquared.outsourcing_simulation.src.util.custom_views.RecyclerMessageView

class FindMyTownAdapter(context: Context,arrayList: ArrayList<FindMyTownResponse>,val service: FindMyTownService,recyclerMessageView: RecyclerMessageView):BaseRecyclerMessageViewAdapter<FindMyTownResponse,FindMyTownViewHolder>(context,arrayList,recyclerMessageView) {
    private val inflater=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding: FindTownNearByTownAdapterBinding
    private var clickPosition=-1
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): FindMyTownViewHolder {
        binding = FindTownNearByTownAdapterBinding.inflate(inflater,parent,false)
        binding.root.setOnClickListener {
            clickPosition = arrayList[position].idx
            service.tryPostRegisterAddress(RegisterAddressRequest(clickPosition))
        }
        return FindMyTownViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FindMyTownViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    fun getClickPosition(): Int {
        return clickPosition
    }
}