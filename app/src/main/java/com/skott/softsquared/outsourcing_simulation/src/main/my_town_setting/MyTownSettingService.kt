package com.skott.softsquared.outsourcing_simulation.src.main.my_town_setting

import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.my_town_setting.model.ChangeMyTownRequest
import com.skott.softsquared.outsourcing_simulation.src.main.my_town_setting.model.MyTownSettingResponse
import com.skott.softsquared.outsourcing_simulation.src.main.my_town_setting.model.RangeUpdateRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyTownSettingService(val view:MyTownSettingActivity) {
    fun tryGetMyTown()
    {
        val api= ApplicationClass.sRetrofit.create(MyTownSettingRetrofitInterface::class.java)
        api.getMyTown().enqueue(object: Callback<BaseResponse<ArrayList<MyTownSettingResponse>>> {
            override fun onResponse(
                call: Call<BaseResponse<ArrayList<MyTownSettingResponse>>>,
                response: Response<BaseResponse<ArrayList<MyTownSettingResponse>>>
            ) {
                if(response.body()!!.code!=1000)
                {
                    view.onGetMyTownFailure(response.body()!!.message!!)
                    return
                }
                view.onGetMyTownSuccess(response.body()!!.result!!)
            }

            override fun onFailure(
                call: Call<BaseResponse<ArrayList<MyTownSettingResponse>>>,
                t: Throwable
            ) {
                view.onGetMyTownFailure(t.message?:"지도 조회 에러")
            }

        })
    }
    fun tryPatchMyTown(changeMyTownRequest: ChangeMyTownRequest)
    {
        val api= ApplicationClass.sRetrofit.create(MyTownSettingRetrofitInterface::class.java)
        api.patchMyTown(changeMyTownRequest).enqueue(object: Callback<BaseResponse<String>> {
            override fun onResponse(
                call: Call<BaseResponse<String>>,
                response: Response<BaseResponse<String>>
            ) {
                if(response.body()!!.code!=1000)
                {
                    view.onPatchMyTownFailure(response.body()!!.message!!)
                    return
                }
                view.onPatchMyTownSuccess()
            }

            override fun onFailure(
                call: Call<BaseResponse<String>>,
                t: Throwable
            ) {
                view.onPatchMyTownFailure(t.message?:"동네 변경 에러")
            }

        })
    }
    fun tryPatchDeleteMyTown(changeMyTownRequest: ChangeMyTownRequest)
    {
        val api= ApplicationClass.sRetrofit.create(MyTownSettingRetrofitInterface::class.java)
        api.patchDeleteMyTown(changeMyTownRequest).enqueue(object: Callback<BaseResponse<String>> {
            override fun onResponse(
                call: Call<BaseResponse<String>>,
                response: Response<BaseResponse<String>>
            ) {
                if(response.body()!!.code!=1000)
                {
                    view.onPatchMyTownFailure(response.body()!!.message!!)
                    return
                }
                view.onPatchDeleteMyTownSuccess()
            }

            override fun onFailure(
                call: Call<BaseResponse<String>>,
                t: Throwable
            ) {
                view.onPatchMyTownFailure(t.message?:"동네 삭제 에러")
            }

        })
    }
    fun tryPatchRangeUpdate(rangeUpdateRequest: RangeUpdateRequest)
    {
        val api= ApplicationClass.sRetrofit.create(MyTownSettingRetrofitInterface::class.java)
        api.patchRangeUpdate(rangeUpdateRequest).enqueue(object: Callback<BaseResponse<String>> {
            override fun onResponse(
                call: Call<BaseResponse<String>>,
                response: Response<BaseResponse<String>>
            ) {
                if(response.body()!!.code!=1000)
                {
                    view.onPatchRangeUpdateFailure(response.body()!!.message!!)
                    return
                }
                view.onPatchRangeUpdateSuccess()
            }

            override fun onFailure(
                call: Call<BaseResponse<String>>,
                t: Throwable
            ) {
                view.onPatchRangeUpdateFailure(t.message?:"동네 삭제 에러")
            }

        })
    }
}