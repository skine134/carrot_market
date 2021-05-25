package com.skott.softsquared.outsourcing_simulation.src.main.signin

import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.signin.models.SignInRequest
import com.skott.softsquared.outsourcing_simulation.src.main.signin.models.SignInResponse
import com.skott.softsquared.outsourcing_simulation.src.main.signup.SignupRetrofitInterface
import com.skott.softsquared.outsourcing_simulation.src.main.signup.models.SignUpRequest
import com.skott.softsquared.outsourcing_simulation.src.main.signup.models.SignUpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInService(val view:SignInActivity) {
    fun tryGetJwt(signinRequest: SignInRequest)
    {
        val api = ApplicationClass.sRetrofit.create(SignInRetrofitInterface::class.java)
        api.postSignIn(signinRequest).enqueue(object:
            Callback<BaseResponse<SignInResponse>> {
            override fun onResponse(
                call: Call<BaseResponse<SignInResponse>>,
                response: Response<BaseResponse<SignInResponse>>
            ) {
                if(response.body()!!.code!=1000)
                {
                    view.onSignInFailure(response.body()!!.message?:"에러 발생")
                    return
                }
                view.onSignInSuccess(response.body()!!.result!!)
            }

            override fun onFailure(call: Call<BaseResponse<SignInResponse>>, t: Throwable) {
                view.onSignInFailure(t.message?:"에러 발생")
            }
        })

    }
    fun tryGetCertifications(signUpRequest: SignUpRequest){
        val api =  ApplicationClass.sRetrofit.create(SignupRetrofitInterface::class.java)
        api.postCertifications(signUpRequest).enqueue(object :
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