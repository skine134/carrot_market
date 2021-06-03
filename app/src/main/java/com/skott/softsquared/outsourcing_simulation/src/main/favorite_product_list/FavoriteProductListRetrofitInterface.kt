package com.skott.softsquared.outsourcing_simulation.src.main.favorite_product_list

import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.favorite_product_list.model.FavoriteItemResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FavoriteProductListRetrofitInterface {
    @GET("/app/likes/items")
    fun getFavoriteItems(): Call<BaseResponse<ArrayList<FavoriteItemResponse>>>

    @POST("/app/likes/items/{itemIdx}")
    fun getFavoriteProduct(
        @Path("itemIdx") itemIndex: Int
    ): Call<BaseResponse<String>>
}