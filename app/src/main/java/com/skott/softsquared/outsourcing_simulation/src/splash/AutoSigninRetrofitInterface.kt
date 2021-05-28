package com.skott.softsquared.outsourcing_simulation.src.splash

import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.certification.models.MobileCheckRequest
import com.skott.softsquared.outsourcing_simulation.src.main.certification.models.SignInResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInRetrofitInterface {
    @POST("/signin")
    fun postSignIn(
        @Body MobileCheckRequest: MobileCheckRequest
    ): Call<BaseResponse<SignInResponse>>
}