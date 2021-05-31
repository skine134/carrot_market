package com.skott.softsquared.outsourcing_simulation.src.main.favorite_product_list_fragment

import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.favorite_product_list_fragment.model.FavoriteItemModel
import retrofit2.Call
import retrofit2.http.GET

interface FavoriteProductListRetrofitInterface {
    @GET("/app/likes/items")
    fun getFavoriteItems(): Call<BaseResponse<ArrayList<FavoriteItemModel>>>
}