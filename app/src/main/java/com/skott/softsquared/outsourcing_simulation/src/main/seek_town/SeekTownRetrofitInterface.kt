package com.skott.softsquared.outsourcing_simulation.src.main.seek_town

import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.seek_town.model.TownResponse
import retrofit2.Call
import retrofit2.http.GET

interface SeekTownRetrofitInterface {
    @GET("/app/locations/my-villages")
    fun getTowns(): Call<BaseResponse<TownResponse>>
}