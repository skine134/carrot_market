package com.skott.softsquared.outsourcing_simulation.src.main.profile

import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.profile.model.ProfileResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProfileRetrofitInterface {
    @GET("/app/users/profiles/{userIdx}")
    fun getProfile(
        @Path("userIdx") userIdx:Int
    ): Call<BaseResponse<ProfileResponse>>
}