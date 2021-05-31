package com.skott.softsquared.outsourcing_simulation.src.main.favorite_product_list_fragment

import com.skott.softsquared.outsourcing_simulation.src.main.favorite_product_list_fragment.model.FavoriteItemModel

interface FavoriteProductListView {
    fun onGetFavoriteListViewSuccess(favoriteItemModelArray: ArrayList<FavoriteItemModel>)
    fun onGetFavoriteListViewFailure(message:String)
}