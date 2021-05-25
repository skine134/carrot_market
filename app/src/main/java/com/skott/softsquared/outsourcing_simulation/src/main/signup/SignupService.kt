package com.skott.softsquared.outsourcing_simulation.src.main.signup

import com.skott.softsquared.outsourcing_simulation.src.util.lib.getAPIHandler
import com.skott.softsquared.outsourcing_simulation.BuildConfig
import com.skott.softsquared.outsourcing_simulation.src.BaseModel
import com.skott.softsquared.outsourcing_simulation.src.main.signup.models.CertificationsRequest
import com.skott.softsquared.outsourcing_simulation.src.main.signup.models.CertificationsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupService(val view: SignupActivity)
{
    fun tryGetCertifications(certificationsRequest: CertificationsRequest){
        val certificationRetrofitInterface = getAPIHandler(BuildConfig.SERVER_URL,SignupRetrofitInterface::class)
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