package com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list

import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list.model.SaleProductListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface SaleProductListRetrofitInterface {
    @GET("/app/users/selling-items")
    fun getSaleProductList(): Call<BaseResponse<ArrayList<SaleProductListResponse>>>
    @POST("/app/deals")
    fun postSoldOut(): Call<BaseResponse<ArrayList<SaleProductListResponse>>>
}