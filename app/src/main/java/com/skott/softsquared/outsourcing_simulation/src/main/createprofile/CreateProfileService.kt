package com.skott.softsquared.outsourcing_simulation.src.main.createprofile

import com.skott.androidUtil.Lib.getAPIHandler
import com.skott.softsquared.outsourcing_simulation.BuildConfig
import com.skott.softsquared.outsourcing_simulation.src.BaseModel
import com.skott.softsquared.outsourcing_simulation.src.main.createprofile.models.SignupRequest
import com.skott.softsquared.outsourcing_simulation.src.main.createprofile.models.SignupResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateProfileService(val view: CreateProfileActivity) {
    fun tryGetJwt(signupRequest: SignupRequest)
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
                view.jwtListener(response.body()!!.result)
            }

            override fun onFailure(call: Call<BaseModel<SignupResponse>>, t: Throwable) {
                view.signUpErrorListener(t.message?:"에러 발생")
            }

        })
    }
}