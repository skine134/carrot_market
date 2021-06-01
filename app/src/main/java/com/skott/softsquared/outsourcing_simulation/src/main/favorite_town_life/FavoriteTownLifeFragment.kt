package com.skott.softsquared.outsourcing_simulation.src.main.favorite_town_life

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.src.main.favorite_list.BaseFavoriteListFragment

class FavoriteTownLifeFragment: BaseFavoriteListFragment() {
    override var tabName =""
    override var emptyMessage= ""
    override var service: () -> Unit = { Log.d("not implement","")}
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tabName = requireContext().getString(R.string.favorite_list_host_town_life)
        emptyMessage= requireContext().getString(R.string.favorite_list_not_found_favorite_list).replace("name","동네생활 글")
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }
}