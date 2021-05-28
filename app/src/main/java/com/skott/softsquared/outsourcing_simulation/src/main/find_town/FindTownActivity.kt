package com.skott.softsquared.outsourcing_simulation.src.main.find_town

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.skott.softsquared.outsourcing_simulation.databinding.FindTownByCurrentLocationBinding
import com.skott.softsquared.outsourcing_simulation.src.config.BaseActivity
import com.skott.softsquared.outsourcing_simulation.src.main.find_town.model.TownInfo

class FindTownActivity :BaseActivity<FindTownByCurrentLocationBinding>(FindTownByCurrentLocationBinding::inflate){
    private lateinit var context: Context
    private lateinit var adapter:FindMyTownAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val arrayList = ArrayList<TownInfo>()
        context = this
        adapter = FindMyTownAdapter(context,arrayList,binding.findTownRecyclerMessageView)
        binding.findTownRecyclerMessageView.getRecyclerView().layoutManager = LinearLayoutManager(context)
        binding.findTownRecyclerMessageView.getRecyclerView().adapter = adapter
    }
}