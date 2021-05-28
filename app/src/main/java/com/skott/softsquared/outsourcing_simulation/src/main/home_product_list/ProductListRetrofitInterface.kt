package com.skott.softsquared.outsourcing_simulation.src.main.home_product_list

import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.home_product_list.model.ProductListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryName

interface ProductListRetrofitInterface {
    @GET("/app/items")
    fun getItems(
        @Query("villageIdx") villageIdx:Int,
        @Query("rangeLevel") rangeLevel:Int,
        @Query("categories") categories:String,
        @Query("lastItemIdx") lastItemIdx:Int,
        @Query("numOfPages") numOfPages:Int
    ): Call<BaseResponse<ArrayList<ProductListResponse>>>
}