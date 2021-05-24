package com.skott.softsquared.outsourcing_simulation.src.main.signin

import com.skott.softsquared.outsourcing_simulation.src.BaseModel
import com.skott.softsquared.outsourcing_simulation.src.main.signin.models.SigninRequest
import com.skott.softsquared.outsourcing_simulation.src.main.signin.models.SigninResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SigninRetrofitInterface {
    @POST("/signin")
    fun postSignin(
        @Body signinRequest: SigninRequest
    ): Call<BaseModel<SigninResponse>>
}