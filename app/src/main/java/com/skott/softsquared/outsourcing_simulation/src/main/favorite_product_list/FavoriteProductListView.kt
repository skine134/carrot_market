package com.skott.softsquared.outsourcing_simulation.src.main.favorite_product_list

import com.skott.softsquared.outsourcing_simulation.src.main.favorite_product_list.model.FavoriteItemResponse

interface FavoriteProductListView {
    fun onGetFavoriteListViewSuccess(favoriteItemResponseArray: ArrayList<FavoriteItemResponse>)
    fun onGetFavoriteListViewFailure(message:String)
}