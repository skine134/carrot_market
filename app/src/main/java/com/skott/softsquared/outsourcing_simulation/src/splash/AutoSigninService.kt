package com.skott.softsquared.outsourcing_simulation.src.splash

import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.certification.models.SignInResponse
import com.skott.softsquared.outsourcing_simulation.src.splash.model.AutoSignInResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AutoSignInService(val view:SplashActivity) {
    fun tryGetAutoSignIn()
    {
        val api = ApplicationClass.sRetrofit.create(AutoSigninRetrofitInterface::class.java)
        api.postSignIn().enqueue(object:
            Callback<BaseResponse<ArrayList<AutoSignInResponse>>> {
            override fun onResponse(
                call: Call<BaseResponse<ArrayList<AutoSignInResponse>>>,
                response: Response<BaseResponse<ArrayList<AutoSignInResponse>>>
            ) {
                if(response.body()!!.code!=1000)
                {
                    view.onAutoSignInFailure(response.body()!!.message?:"에러 발생")
                    return
                }
                view.onAutoSignInSuccess(response.body()!!.result!!)
            }

            override fun onFailure(call: Call<BaseResponse<ArrayList<AutoSignInResponse>>>, t: Throwable) {
                view.onAutoSignInFailure(t.message?:"에러 발생")
            }
        })

    }
}