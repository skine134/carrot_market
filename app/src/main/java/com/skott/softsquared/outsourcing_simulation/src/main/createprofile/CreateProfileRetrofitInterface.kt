package com.skott.softsquared.outsourcing_simulation.src.main.createprofile

import com.skott.softsquared.outsourcing_simulation.src.BaseModel
import com.skott.softsquared.outsourcing_simulation.src.main.createprofile.models.SignupRequest
import com.skott.softsquared.outsourcing_simulation.src.main.createprofile.models.SignupResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface CreateProfileRetrofitInterface {
    @POST("/signup")
    fun getJwt(
        @Body signupRequest: SignupRequest
    ): Call<BaseModel<SignupResponse>>
}