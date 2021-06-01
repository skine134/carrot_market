package com.skott.softsquared.outsourcing_simulation.src.main.buy_list

import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.buy_list.model.BuyListResponse
import retrofit2.Call
import retrofit2.http.GET

interface BuyListRetrofitInterface {
    @GET("/app/users/purchased-items")
    fun getBuyList(
    ): Call<BaseResponse<ArrayList<BuyListResponse>>>
}