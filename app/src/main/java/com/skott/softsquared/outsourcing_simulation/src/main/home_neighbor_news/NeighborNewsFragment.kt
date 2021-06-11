package com.skott.softsquared.outsourcing_simulation.src.main.home_neighbor_news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skott.softsquared.outsourcing_simulation.src.config.BaseFragment
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.TownNewsFragmentBinding
import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.main.certification.models.UserLocation
import com.skott.softsquared.outsourcing_simulation.src.main.find_town.model.FindMyTownResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class NeighborNewsFragment :BaseFragment<TownNewsFragmentBinding>(TownNewsFragmentBinding::bind, R.layout.town_news_fragment) {
    private val dong= ArrayList<String>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val location = ApplicationClass.sSharedPreferences.getString(requireContext().getString(R.string.location_key),"")
        if(!location.equals(""))
        {
            try{
                val data = Json.decodeFromString<ArrayList<UserLocation>>(location!!)
                for(item in data)
                {
                    dong.add(item.dong)
                }
            }catch (e:Exception)
            {
                val data = Json.decodeFromString<FindMyTownResponse>(location!!)
                dong.add(data.village)
            }
            val splitTownName = dong[0].split(" ")
            if (splitTownName.isNotEmpty())
                binding.town.text = splitTownName[splitTownName.size-1]
            else
                binding.town.text = dong[0]
        }
        return binding.root
    }
}