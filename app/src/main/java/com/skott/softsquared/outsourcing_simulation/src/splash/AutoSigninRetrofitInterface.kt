package com.skott.softsquared.outsourcing_simulation.src.splash

import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.splash.model.AutoSignInResponse
import retrofit2.Call
import retrofit2.http.POST

interface AutoSigninRetrofitInterface {
    @POST("/app/auto-login")
    fun postSignIn(): Call<BaseResponse<ArrayList<AutoSignInResponse>>>
}