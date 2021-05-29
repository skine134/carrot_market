package com.skott.softsquared.outsourcing_simulation.src.main.product_detail

import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.product_detail.model.ProductDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductDetailRetrofitInterface {
    @GET("/app/items/{itemIdx}")
    fun getProductDetail(
        @Path("itemIdx") itemIndex:Int
    ): Call<BaseResponse<ProductDetailResponse>>

}