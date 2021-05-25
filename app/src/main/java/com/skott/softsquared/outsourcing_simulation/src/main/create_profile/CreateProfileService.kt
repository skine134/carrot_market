package com.skott.softsquared.outsourcing_simulation.src.main.create_profile

import com.skott.androidUtil.Lib.getAPIHandler
import com.skott.softsquared.outsourcing_simulation.BuildConfig
import com.skott.softsquared.outsourcing_simulation.src.BaseModel
import com.skott.softsquared.outsourcing_simulation.src.main.create_profile.models.SignupRequest
import com.skott.softsquared.outsourcing_simulation.src.main.create_profile.models.SignupResponse
import com.skott.softsquared.outsourcing_simulation.src.main.signin.SigninRetrofitInterface
import com.skott.softsquared.outsourcing_simulation.src.main.signin.models.SigninRequest
import com.skott.softsquared.outsourcing_simulation.src.main.signin.models.SigninResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateProfileService(val view: CreateProfileActivity) {
    fun trySignup(signupRequest: SignupRequest)
    {
        val api = getAPIHandler(BuildConfig.SERVER_URL,CreateProfileRetrofitInterface::class)
        api.getJwt(signupRequest).enqueue(object: Callback<BaseModel<SignupResponse>> {
            override fun onResponse(
                call: Call<BaseModel<SignupResponse>>,
                response: Response<BaseModel<SignupResponse>>
            ) {
                //TODO 나중에 jwt 들어오면 jwt값 저장.
                if(response.body()!!.code!=1000)
                {
                    view.signUpErrorListener(response.body()!!.message)
                    return
                }
                view.signupResponseListener(    response.body()!!.result)
            }

            override fun onFailure(call: Call<BaseModel<SignupResponse>>, t: Throwable) {
                view.signUpErrorListener(t.message?:"에러 발생")
            }

        })
    }

    fun tryGetJwt(signinRequest: SigninRequest)
    {
        val signInInterface= getAPIHandler(BuildConfig.SERVER_URL, SigninRetrofitInterface::class)
        signInInterface.postSignin(signinRequest).enqueue(object:
            Callback<BaseModel<SigninResponse>> {
            override fun onResponse(
                call: Call<BaseModel<SigninResponse>>,
                response: Response<BaseModel<SigninResponse>>
            ) {
                if(response.body()!!.code!=1000)
                {
                    view.signInErrorListener(response.body()!!.message)
                    return
                }
                view.jwtListener(response.body()!!.result)
            }

            override fun onFailure(call: Call<BaseModel<SigninResponse>>, t: Throwable) {
                view.signInErrorListener(t.message?:"에러 발생")
            }
        })

    }
}