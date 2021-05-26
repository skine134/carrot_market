package com.skott.softsquared.outsourcing_simulation.src.main.signup

import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.signup.models.SignUpRequest
import com.skott.softsquared.outsourcing_simulation.src.main.signup.models.SignUpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupService(val view: SignupActivity)
{
    fun tryGetCertifications(signUpRequest: SignUpRequest){
        val certificationRetrofitInterface = ApplicationClass.sRetrofit.create(SignupRetrofitInterface::class.java)
        certificationRetrofitInterface.postCertifications(signUpRequest).enqueue(object :
            Callback<BaseResponse<SignUpResponse>> {
            override fun onResponse(
                call: Call<BaseResponse<SignUpResponse>>,
                response: Response<BaseResponse<SignUpResponse>>
            ) {
                view.onCertificationsSuccess(response.body()!!.result!!)
            }

            override fun onFailure(call: Call<BaseResponse<SignUpResponse>>, t: Throwable) {
                view.onCertificationsFailure(t.message?:"통신 오류")
            }
        })
    }
}