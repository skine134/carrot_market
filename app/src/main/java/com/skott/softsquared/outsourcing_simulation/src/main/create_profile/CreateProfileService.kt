package com.skott.softsquared.outsourcing_simulation.src.main.create_profile

import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.create_profile.models.SignupRequest
import com.skott.softsquared.outsourcing_simulation.src.main.create_profile.models.SignupResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateProfileService(val view: CreateProfileActivity) {
    fun trySignUp(signupRequest: SignupRequest)
    {
        val api = ApplicationClass.sRetrofit.create(CreateProfileRetrofitInterface::class.java)
        api.postJwt(signupRequest).enqueue(object: Callback<BaseResponse<SignupResponse>> {
            override fun onResponse(
                call: Call<BaseResponse<SignupResponse>>,
                response: Response<BaseResponse<SignupResponse>>
            ) {
                if(response.body()!!.code!=1000)
                {
                    view.onSignUpFailure(response.body()!!.message!!)
                    return
                }
                view.onSignUpSuccess(    response.body()!!.result!!)
            }

            override fun onFailure(call: Call<BaseResponse<SignupResponse>>, t: Throwable) {
                view.onSignUpFailure(t.message?:"에러 발생")
            }

        })
    }
}