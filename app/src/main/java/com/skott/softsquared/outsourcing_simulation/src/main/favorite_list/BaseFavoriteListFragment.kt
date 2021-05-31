package com.skott.softsquared.outsourcing_simulation.src.main.favorite_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.FavoriteListFragmentBinding
import com.skott.softsquared.outsourcing_simulation.src.config.BaseFragment
import com.skott.softsquared.outsourcing_simulation.src.util.lib.getScrollListener

abstract class BaseFavoriteListFragment : BaseFragment<FavoriteListFragmentBinding>(
    FavoriteListFragmentBinding::bind,
    R.layout.favorite_list_fragment
) {
    //    abstract var adapter: RecyclerView.Adapter<VH>
    abstract var tabName: String
    abstract var emptyMessage: String
    abstract var service: () -> Unit
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         super.onCreateView(inflater, container, savedInstanceState)
        binding.favoriteList.message = emptyMessage
//        binding.favoriteList.getRecyclerView().addOnScrollListener(getScrollListener {
//            service()
//        })
        service()
        return binding.root
    }
}