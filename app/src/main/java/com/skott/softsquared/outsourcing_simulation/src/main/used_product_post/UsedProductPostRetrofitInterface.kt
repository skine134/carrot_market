package com.skott.softsquared.outsourcing_simulation.src.main.used_product_post

import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.used_product_post.model.UsedProductPostRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UsedProductPostRetrofitInterface {
    @POST("/app/items")
    fun postItemUpload(
        @Body usedProductPostRequest: UsedProductPostRequest
    ):Call<BaseResponse<String>>

}