package com.skott.softsquared.outsourcing_simulation.src.main.signup

import com.skott.softsquared.outsourcing_simulation.src.BaseModel
import com.skott.softsquared.outsourcing_simulation.src.main.signup.models.SignUpRequest
import com.skott.softsquared.outsourcing_simulation.src.main.signup.models.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignupRetrofitInterface
{
    @POST("/certifications")
    fun postCertifications(
        @Body params: SignUpRequest
    ): Call<BaseModel<SignUpResponse>>
}