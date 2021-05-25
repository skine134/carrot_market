package com.skott.softsquared.outsourcing_simulation.src.main.create_profile

import com.skott.softsquared.outsourcing_simulation.src.util.lib.getAPIHandler
import com.skott.softsquared.outsourcing_simulation.BuildConfig
import com.skott.softsquared.outsourcing_simulation.src.BaseModel
import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.main.create_profile.models.SignupRequest
import com.skott.softsquared.outsourcing_simulation.src.main.create_profile.models.SignupResponse
import com.skott.softsquared.outsourcing_simulation.src.main.signin.SigninRetrofitInterface
import com.skott.softsquared.outsourcing_simulation.src.main.signin.models.SigninRequest
import com.skott.softsquared.outsourcing_simulation.src.main.signin.models.SigninResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateProfileService(val view: CreateProfileActivity) {
    fun trySignUp(signupRequest: SignupRequest)
    {
        val api = ApplicationClass.sRetrofit.create(CreateProfileRetrofitInterface::class.java)
        api.postJwt(signupRequest).enqueue(object: Callback<BaseModel<SignupResponse>> {
            override fun onResponse(
                call: Call<BaseModel<SignupResponse>>,
                response: Response<BaseModel<SignupResponse>>
            ) {
                //TODO 나중에 jwt 들어오면 jwt값 저장.
                if(response.body()!!.code!=1000)
                {
                    view.onSignUpFailure(response.body()!!.message)
                    return
                }
                view.onSignUpSuccess(    response.body()!!.result)
            }

            override fun onFailure(call: Call<BaseModel<SignupResponse>>, t: Throwable) {
                view.onSignUpFailure(t.message?:"에러 발생")
            }

        })
    }

    fun trySignIn(signinRequest: SigninRequest)
    {
        val signInInterface= ApplicationClass.sRetrofit.create(SigninRetrofitInterface::class.java)
        signInInterface.postSignin(signinRequest).enqueue(object:
            Callback<BaseModel<SigninResponse>> {
            override fun onResponse(
                call: Call<BaseModel<SigninResponse>>,
                response: Response<BaseModel<SigninResponse>>
            ) {
                if(response.body()!!.code!=1000)
                {
                    view.onSignInFailure(response.body()!!.message)
                    return
                }
                view.onSignInSuccess(response.body()!!.result)
            }

            override fun onFailure(call: Call<BaseModel<SigninResponse>>, t: Throwable) {
                view.onSignInFailure(t.message?:"에러 발생")
            }
        })

    }
}