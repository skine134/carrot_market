package com.skott.softsquared.outsourcing_simulation.src.main.sold_product_list

import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list.model.SaleProductListResponse
import com.skott.softsquared.outsourcing_simulation.src.main.sold_product_list.model.SoldProductListResponse
import retrofit2.Call
import retrofit2.http.GET

interface SoldProductListRetrofitInterface {
    @GET("/app/users/sold-items")
    fun getSoldProductList(): Call<BaseResponse<ArrayList<SoldProductListResponse>>>
}