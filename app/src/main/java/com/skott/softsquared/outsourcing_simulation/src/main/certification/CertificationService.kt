package com.skott.softsquared.outsourcing_simulation.src.main.certification

import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.certification.models.CertificationResponse
import com.skott.softsquared.outsourcing_simulation.src.main.certification.models.MobileCheckRequest
import com.skott.softsquared.outsourcing_simulation.src.main.certification.models.SignInResponse
import com.skott.softsquared.outsourcing_simulation.src.splash.SignInRetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CertificationService(val view: CertificationActivity)
{

    fun tryGetJwt(MobileCheckRequest: MobileCheckRequest)
    {
        val api = ApplicationClass.sRetrofit.create(CertificationRetrofitInterface::class.java)
        api.postSignIn(MobileCheckRequest).enqueue(object:
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
    fun tryPostSignUpMobileCheck(mobileCheckRequest: MobileCheckRequest)
    {
        val api = ApplicationClass.sRetrofit.create(CertificationRetrofitInterface::class.java)
        api.postSignUpMobileCheck(mobileCheckRequest).enqueue(object:
            Callback<BaseResponse<String>> {
            override fun onResponse(
                call: Call<BaseResponse<String>>,
                response: Response<BaseResponse<String>>
            ) {
                if(response.body()!!.code!=1000)
                {
                    view.onSignInFailure(response.body()!!.message?:"에러 발생")
                    return
                }
                view.onSignUpMobileCheckSuccess()
            }

            override fun onFailure(call: Call<BaseResponse<String>>, t: Throwable) {
                view.onSignInFailure(t.message?:"에러 발생")
            }
        })
    }
    fun tryGetCertifications(cellphone:String){
        val certificationRetrofitInterface = ApplicationClass.sRetrofit.create(CertificationRetrofitInterface::class.java)
        certificationRetrofitInterface.getCertifications(cellphone).enqueue(object :
            Callback<BaseResponse<CertificationResponse>> {
            override fun onResponse(
                call: Call<BaseResponse<CertificationResponse>>,
                response: Response<BaseResponse<CertificationResponse>>
            ) {
                view.onCertificationsSuccess(response.body()!!.result!!)
            }

            override fun onFailure(call: Call<BaseResponse<CertificationResponse>>, t: Throwable) {
                view.onCertificationsFailure(t.message?:"통신 오류")
            }
        })
    }
}