package com.skott.softsquared.outsourcing_simulation.src.main.signin

import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.signin.models.SignInRequest
import com.skott.softsquared.outsourcing_simulation.src.main.signin.models.SignInResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInRetrofitInterface {
    @POST("/signin")
    fun postSignIn(
        @Body signInRequest: SignInRequest
    ): Call<BaseResponse<SignInResponse>>
}