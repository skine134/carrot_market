package com.skott.softsquared.outsourcing_simulation.src.main.collect_user

import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.collect_user.model.CollectUserResponse
import retrofit2.Call
import retrofit2.http.GET

interface CollectUserRetrofitInterface {
    @GET("/app/likes/sellers")
    fun getCollectUser(): Call<BaseResponse<ArrayList<CollectUserResponse>>>
}