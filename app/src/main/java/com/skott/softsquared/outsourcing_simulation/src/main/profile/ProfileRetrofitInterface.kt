package com.skott.softsquared.outsourcing_simulation.src.main.profile

import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.profile.model.ProfileResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProfileRetrofitInterface {
    @GET("/app/users/profiles/{userIdx}")
    fun getProfile(
        @Path("userIdx") userIdx:Int
    ): Call<BaseResponse<ProfileResponse>>

    @POST("/app/likes/sellers/{sellerIdx}")
    fun postCollectSeller(
        @Path("sellerIdx") userIdx:Int
    ): Call<BaseResponse<String>>
}