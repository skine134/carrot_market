package com.skott.softsquared.outsourcing_simulation.src.main.sold_product_list

import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list.model.SaleProductListResponse
import com.skott.softsquared.outsourcing_simulation.src.main.sold_product_list.model.BuyerListResponse
import com.skott.softsquared.outsourcing_simulation.src.main.sold_product_list.model.SoldProductListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Query

interface SoldProductListRetrofitInterface {
    @GET("/app/users/sold-items")
    fun getSoldProductList(): Call<BaseResponse<ArrayList<SoldProductListResponse>>>
    @PATCH("/app/deals")
    fun patchSale(
        @Query("itemIdx") itemIndex:Int
    ):Call<BaseResponse<String>>
    @GET("/app/deals/buyers")
    fun getBuyerList(
        @Query("itemIdx") itemIndex:Int):Call<BaseResponse<ArrayList<BuyerListResponse>>>
}