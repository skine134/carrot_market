package com.skott.softsquared.outsourcing_simulation.src.main.signup

import com.skott.softsquared.outsourcing_simulation.src.BaseModel
import com.skott.softsquared.outsourcing_simulation.src.main.signup.models.CertificationsRequest
import com.skott.softsquared.outsourcing_simulation.src.main.signup.models.CertificationsResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SignupRetrofitInterface
{
    @POST("/certifications")
    fun getCertifications(
        @Body params: CertificationsRequest
    ): Call<BaseModel<CertificationsResponse>>
}