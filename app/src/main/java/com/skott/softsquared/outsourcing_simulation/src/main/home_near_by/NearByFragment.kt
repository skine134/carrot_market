package com.skott.softsquared.outsourcing_simulation.src.main.home_near_by

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.skott.softsquared.outsourcing_simulation.src.config.BaseFragment
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.NearByAdapterBinding
import com.skott.softsquared.outsourcing_simulation.databinding.NearByFragmentBinding
import com.skott.softsquared.outsourcing_simulation.src.main.home_near_by.model.NearbyPostModel

class NearByFragment :
    BaseFragment<NearByFragmentBinding>(NearByFragmentBinding::bind, R.layout.near_by_fragment) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val arrayList = ArrayList<NearbyPostModel>()
        setModel(arrayList)
        val layoutManager= LinearLayoutManager(context)
        val adapter = NearbyAdapter(requireContext(),arrayList)
        binding.nearByNewsRecyclerView.getRecyclerView().layoutManager=layoutManager
        binding.nearByNewsRecyclerView.setAdapter(adapter)
        return binding.root
    }

    private fun setModel(arrayList: ArrayList<NearbyPostModel>) {   /*
        image: String,
    title: String,
    town: String,
    content: String,
    profile: String,
    name: String

        */
        for (i in 0..5)
            arrayList.add(
                NearbyPostModel(
                    "https://firebasestorage.googleapis.com/v0/b/softsquaredcarrotmarketc.appspot.com/o/image%2F5476c2bd-1ec4-4099-933c-ff98e6fa8f87?alt=media&token=7baa8ad0-c6c1-4bdd-b80d-8b0f5a7b4c4f",
                    "같이 노실 분 있나요??",
                    "지금동",
                    "보드 게임 하면서 같이 노실 분 구합니다.",
                    "https://firebasestorage.googleapis.com/v0/b/softsquaredcarrotmarketc.appspot.com/o/image%2F0cfa23d7-8b69-4e5d-a4be-85ebca5ac83d?alt=media&token=39d3d208-6e43-4db0-8129-14b8b841cdd8",
                            "스캇")
                )

    }
}