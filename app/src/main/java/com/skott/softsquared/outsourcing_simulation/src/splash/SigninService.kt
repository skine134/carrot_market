package com.skott.softsquared.outsourcing_simulation.src.splash

class SignInService(val view:SplashActivity) {
//    fun tryGetJwt(MobileCheckRequest: MobileCheckRequest)
//    {
//        val api = ApplicationClass.sRetrofit.create(SignInRetrofitInterface::class.java)
//        api.postSignIn(MobileCheckRequest).enqueue(object:
//            Callback<BaseResponse<SignInResponse>> {
//            override fun onResponse(
//                call: Call<BaseResponse<SignInResponse>>,
//                response: Response<BaseResponse<SignInResponse>>
//            ) {
//                if(response.body()!!.code!=1000)
//                {
//                    view.onSignInFailure(response.body()!!.message?:"에러 발생")
//                    return
//                }
//                view.onSignInSuccess(response.body()!!.result!!)
//            }
//
//            override fun onFailure(call: Call<BaseResponse<SignInResponse>>, t: Throwable) {
//                view.onSignInFailure(t.message?:"에러 발생")
//            }
//        })
//
//    }
}