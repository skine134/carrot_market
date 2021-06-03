package com.skott.softsquared.outsourcing_simulation.src.main.buyer_select

import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.buyer_select.model.PostBuyerRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface BuyerSelectRetrofitInterface {
    @POST("/app/deals/buyers")
    fun postBuyerSelect(
        @Body postBuyerRequest:PostBuyerRequest
    ): Call<BaseResponse<String>>
}