package com.skott.softsquared.outsourcing_simulation.src.main.favorite_product_list_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.favorite_list.BaseFavoriteListFragment
import com.skott.softsquared.outsourcing_simulation.src.main.favorite_product_list_fragment.model.FavoriteItemModel
import com.skott.softsquared.outsourcing_simulation.src.main.home_product_list.ProductRecyclerAdapter
import com.skott.softsquared.outsourcing_simulation.src.util.adapters.BaseRecyclerMessageViewAdapter

class FavoriteProductListFragment() : BaseFavoriteListFragment(),
    FavoriteProductListView {
    private lateinit var adapter: FavoriteProductListAdapter
    override var tabName: String = ""
    override var emptyMessage=""
    override var service= {FavoriteProductListService(this).tryGetFavoriteList()}
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tabName = requireContext().getString(R.string.favorite_list_host_used_product)
        emptyMessage = requireContext().getString(R.string.favorite_list_not_found_favorite_list).replace("name","중고거래 글")
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onGetFavoriteListViewSuccess(favoriteItemModelArray:ArrayList<FavoriteItemModel>) {
        adapter= FavoriteProductListAdapter(requireContext(),favoriteItemModelArray,binding.favoriteList)
        binding.favoriteList.getRecyclerView().layoutManager=LinearLayoutManager(context)
        binding.favoriteList.getRecyclerView().adapter=adapter
    }

    override fun onGetFavoriteListViewFailure(message:String) {
        Log.e("api error",message)
    }
}