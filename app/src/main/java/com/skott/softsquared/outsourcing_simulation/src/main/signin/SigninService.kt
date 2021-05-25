package com.skott.softsquared.outsourcing_simulation.src.main.signin

import com.skott.softsquared.outsourcing_simulation.src.util.lib.getAPIHandler
import com.skott.softsquared.outsourcing_simulation.BuildConfig
import com.skott.softsquared.outsourcing_simulation.src.BaseModel
import com.skott.softsquared.outsourcing_simulation.src.main.signin.models.SigninRequest
import com.skott.softsquared.outsourcing_simulation.src.main.signin.models.SigninResponse
import com.skott.softsquared.outsourcing_simulation.src.main.signup.SignupRetrofitInterface
import com.skott.softsquared.outsourcing_simulation.src.main.signup.models.CertificationsRequest
import com.skott.softsquared.outsourcing_simulation.src.main.signup.models.CertificationsResponse
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
    fun tryGetCertifications(certificationsRequest: CertificationsRequest){
        val certificationRetrofitInterface = getAPIHandler(BuildConfig.SERVER_URL,
            SignupRetrofitInterface::class)
        certificationRetrofitInterface.getCertifications(certificationsRequest).enqueue(object :
            Callback<BaseModel<CertificationsResponse>> {
            override fun onResponse(
                call: Call<BaseModel<CertificationsResponse>>,
                response: Response<BaseModel<CertificationsResponse>>
            ) {
                view.certificationsResponseListener(response.body()!!.result)
            }

            override fun onFailure(call: Call<BaseModel<CertificationsResponse>>, t: Throwable) {
                view.certificationsResponseErrorListener(t.message?:"통신 오류")
            }
        })
    }
}