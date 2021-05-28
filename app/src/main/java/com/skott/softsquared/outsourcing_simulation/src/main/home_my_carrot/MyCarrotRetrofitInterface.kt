package com.skott.softsquared.outsourcing_simulation.src.main.home_my_carrot

import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.home_my_carrot.model.MyCarrotResponse
import retrofit2.Call
import retrofit2.http.GET

interface MyCarrotRetrofitInterface {
    @GET("/app/users")
    fun getMyCarrot(): Call<BaseResponse<MyCarrotResponse>>
}