package com.skott.softsquared.outsourcing_simulation.src.main.create_profile

import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.create_profile.models.SignupRequest
import com.skott.softsquared.outsourcing_simulation.src.main.create_profile.models.SignupResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface CreateProfileRetrofitInterface {
    @POST("/signup")
    fun postJwt(
        @Body signupRequest: SignupRequest
    ): Call<BaseResponse<SignupResponse>>
}