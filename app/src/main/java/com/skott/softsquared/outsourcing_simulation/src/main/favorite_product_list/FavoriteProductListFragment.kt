package com.skott.softsquared.outsourcing_simulation.src.main.favorite_product_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.src.main.favorite_list.BaseFavoriteListFragment
import com.skott.softsquared.outsourcing_simulation.src.main.favorite_product_list.model.FavoriteItemResponse

class FavoriteProductListFragment() : BaseFavoriteListFragment(),
    FavoriteProductListView {
    private lateinit var adapter: FavoriteProductListAdapter
    override var tabName: String = ""
    override var emptyMessage=""
    val favoriteProductService = FavoriteProductListService(this)
    override var service= {favoriteProductService.tryGetFavoriteList()}
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

    override fun onGetFavoriteListViewSuccess(favoriteItemResponseArray:ArrayList<FavoriteItemResponse>) {
        adapter= FavoriteProductListAdapter(requireContext(),favoriteItemResponseArray,favoriteProductService,binding.favoriteList)
        binding.favoriteList.getRecyclerView().adapter=adapter
    }

    override fun onGetFavoriteListViewFailure(message:String) {
        Log.e("api error",message)
    }

    override fun onGetFavoriteProductSuccess() {
    }

    override fun onGetFavoriteProductFailure(message: String) {
        Log.e("api error",message)
    }
}