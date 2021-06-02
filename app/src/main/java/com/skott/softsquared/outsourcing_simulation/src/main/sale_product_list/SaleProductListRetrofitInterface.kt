package com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list

import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list.model.SaleProductListResponse
import com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list.model.SoldOutRequest
import com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list.model.SoldResponse
import retrofit2.Call
import retrofit2.http.*

interface SaleProductListRetrofitInterface {
    @GET("/app/users/selling-items")
    fun getSaleProductList(): Call<BaseResponse<ArrayList<SaleProductListResponse>>>
    @POST("/app/deals")
    fun postSoldOut
    (
        @Body soldOutRequest:SoldOutRequest
    ): Call<BaseResponse<ArrayList<SoldResponse>>>

}