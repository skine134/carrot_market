package com.skott.softsquared.outsourcing_simulation.src.main.signin

import com.skott.androidUtil.Lib.getAPIHandler
import com.skott.softsquared.outsourcing_simulation.BuildConfig
import com.skott.softsquared.outsourcing_simulation.src.BaseModel
import com.skott.softsquared.outsourcing_simulation.src.main.signin.models.SigninRequest
import com.skott.softsquared.outsourcing_simulation.src.main.signin.models.SigninResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SigninService(val view:SignInActivity) {
    fun tryGetJwt(signinRequest: SigninRequest)
    {
        val signInInterface= getAPIHandler(BuildConfig.SERVER_URL,SigninRetrofitInterface::class)
        signInInterface.postSignin(signinRequest).enqueue(object:
            Callback<BaseModel<SigninResponse>> {
            override fun onResponse(
                call: Call<BaseModel<SigninResponse>>,
                response: Response<BaseModel<SigninResponse>>
            ) {
                if(response.body()!!.code!=1000)
                {
                    view.jwtErrorListener(response.body()!!.message)
                    return
                }
                view.jwtListener(response.body()!!.result)
            }

            override fun onFailure(call: Call<BaseModel<SigninResponse>>, t: Throwable) {
                view.jwtErrorListener(t.message?:"에러 발생")
            }
        })

    }
}