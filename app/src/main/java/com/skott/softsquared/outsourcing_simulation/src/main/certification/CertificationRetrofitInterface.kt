package com.skott.softsquared.outsourcing_simulation.src.main.certification

import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.certification.models.CertificationResponse
import com.skott.softsquared.outsourcing_simulation.src.main.certification.models.MobileCheckRequest
import com.skott.softsquared.outsourcing_simulation.src.main.certification.models.SignInResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CertificationRetrofitInterface
{
    @GET("/app/users/mobile-check")
    fun getCertifications(
        @Query("mobile") mobile: String
    ): Call<BaseResponse<CertificationResponse>>

    @POST("/app/login")
    fun postSignIn(
        @Body MobileCheckRequest: MobileCheckRequest
    ): Call<BaseResponse<SignInResponse>>
    @POST("/app/users/mobile-check-signup")
    fun postSignUpMobileCheck(
        @Body certificationRequest: MobileCheckRequest
    ): Call<BaseResponse<String>>
}