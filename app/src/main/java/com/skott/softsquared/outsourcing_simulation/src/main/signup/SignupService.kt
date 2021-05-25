package com.skott.softsquared.outsourcing_simulation.src.main.signup

import com.skott.softsquared.outsourcing_simulation.src.util.lib.getAPIHandler
import com.skott.softsquared.outsourcing_simulation.BuildConfig
import com.skott.softsquared.outsourcing_simulation.src.BaseModel
import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
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
            Callback<BaseModel<SignUpResponse>> {
            override fun onResponse(
                call: Call<BaseModel<SignUpResponse>>,
                response: Response<BaseModel<SignUpResponse>>
            ) {
                view.certificationsResponseListener(response.body()!!.result)
            }

            override fun onFailure(call: Call<BaseModel<SignUpResponse>>, t: Throwable) {
                view.certificationsResponseErrorListener(t.message?:"통신 오류")
            }
        })
    }
}