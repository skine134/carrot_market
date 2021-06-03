package com.skott.softsquared.outsourcing_simulation.src.main.create_profile

import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.create_profile.models.SignUpRequest
import com.skott.softsquared.outsourcing_simulation.src.main.create_profile.models.SignupResponse
import com.skott.softsquared.outsourcing_simulation.src.main.find_town.model.RegisterAddressRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface CreateProfileRetrofitInterface {
    @POST("/app/users")
    fun postJwt(
        @Body signupRequest: SignUpRequest
    ): Call<BaseResponse<SignupResponse>>

    //초기에 동네 정보를 등록할때 사용
    @POST("/app/locations/my-villages")
    fun postRegisterAddress(@Body registerAddressRequest: RegisterAddressRequest): Call<BaseResponse<String>>
}