package com.skott.softsquared.outsourcing_simulation.src.main.create_profile

import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.create_profile.models.SignUpRequest
import com.skott.softsquared.outsourcing_simulation.src.main.create_profile.models.SignupResponse
import com.skott.softsquared.outsourcing_simulation.src.main.find_town.FindMyTownRetrofitInterface
import com.skott.softsquared.outsourcing_simulation.src.main.find_town.model.RegisterAddressRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateProfileService(val view: CreateProfileActivity) {
    fun trySignUp(signupRequest: SignUpRequest)
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

    fun tryPostRegisterAddress(registerAddressRequest: RegisterAddressRequest)
    {
        val api = ApplicationClass.sRetrofit.create(FindMyTownRetrofitInterface::class.java)
        api.postRegisterAddress(registerAddressRequest).enqueue(object:Callback<BaseResponse<String>>{
            override fun onResponse(
                call: Call<BaseResponse<String>>,
                response: Response<BaseResponse<String>>
            ) {
                if(response.body()!!.code!=1000) {
                    view.onPostRegisterAddressFailure(response.body()!!.message!!)
                    return
                }
                view.onPostRegisterAddressSuccess()
            }

            override fun onFailure(call: Call<BaseResponse<String>>, t: Throwable) {
                view.onPostRegisterAddressFailure(t.message?:"동네 등록 api 에러")
            }

        })
    }
}