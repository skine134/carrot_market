package com.skott.softsquared.outsourcing_simulation.src.main.profile

import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.profile.model.ProfileResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileService(val view:ProfileActivity) {

    fun tryGetProfile(index:Int)
    {
        val api= ApplicationClass.sRetrofit.create(ProfileRetrofitInterface::class.java)
        api.getProfile(index).enqueue(object: Callback<BaseResponse<ProfileResponse>> {
            override fun onResponse(
                call: Call<BaseResponse<ProfileResponse>>,
                response: Response<BaseResponse<ProfileResponse>>
            ) {

                if(response.body()!!.code!=1000)
                {
                    view.onGetProfileFailure(response.body()!!.message!!)
                    return
                }
                view.onGetProfileSuccess(response.body()!!.result!!)

            }

            override fun onFailure(call: Call<BaseResponse<ProfileResponse>>, t: Throwable) {
                view.onGetProfileFailure(t.message?:"프로필 조회 에러")

            }

        })
    }
    fun tryPostCollectUser(index:Int)
    {
        val api= ApplicationClass.sRetrofit.create(ProfileRetrofitInterface::class.java)
        api.postCollectSeller(index).enqueue(object: Callback<BaseResponse<String>> {
            override fun onResponse(
                call: Call<BaseResponse<String>>,
                response: Response<BaseResponse<String>>
            ) {

                if(response.body()!!.code!=1000)
                {
                    view.onPostCollectUserFailure(response.body()!!.message!!)
                    return
                }
                view.onPostCollectUserSuccess()

            }

            override fun onFailure(call: Call<BaseResponse<String>>, t: Throwable) {
                view.onPostCollectUserFailure(t.message?:"프로필 조회 에러")

            }

        })
    }
}