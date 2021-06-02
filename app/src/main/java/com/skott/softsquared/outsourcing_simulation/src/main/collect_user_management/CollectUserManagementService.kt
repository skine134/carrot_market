package com.skott.softsquared.outsourcing_simulation.src.main.collect_user_management

import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.collect_user.model.CollectUserResponse
import com.skott.softsquared.outsourcing_simulation.src.main.collect_user_management.model.CollectUserManagementResponse
import com.skott.softsquared.outsourcing_simulation.src.main.profile.ProfileRetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CollectUserManagementService(val view: CollectUserManagementActivity) {
    fun tryGetCollectUserList() {
        val api =
            ApplicationClass.sRetrofit.create(CollectUserManagementRetrofitInterface::class.java)
        api.getCollectUserList()
            .enqueue(object : Callback<BaseResponse<ArrayList<CollectUserManagementResponse>>> {
                override fun onResponse(
                    call: Call<BaseResponse<ArrayList<CollectUserManagementResponse>>>,
                    response: Response<BaseResponse<ArrayList<CollectUserManagementResponse>>>
                ) {
                    if(response.body()!!.code!=1000) {
                        view.onGetCollectUserListFailure(response.body()!!.message!!)
                        return
                    }
                    view.onGetCollectUserListSuccess(response.body()!!.result!!)
                }

                override fun onFailure(
                    call: Call<BaseResponse<ArrayList<CollectUserManagementResponse>>>,
                    t: Throwable
                ) {
                    view.onGetCollectUserListFailure(t.message?:"Get Collect User error")
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