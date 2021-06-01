package com.skott.softsquared.outsourcing_simulation.src.main.find_town

import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.find_town.model.FindMyTownResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FindMyTownService(val view:FindTownActivity) {
    fun tryGetTown(searchWord:String)
    {
        val api= ApplicationClass.sRetrofit.create(FindMyTownRetrofitInterface::class.java)
        api.getSearchTown(searchWord).enqueue(object:
            Callback<BaseResponse<ArrayList<FindMyTownResponse>>> {
            override fun onResponse(
                call: Call<BaseResponse<ArrayList<FindMyTownResponse>>>,
                response: Response<BaseResponse<ArrayList<FindMyTownResponse>>>
            ) {
                if(response.body()!!.code!=100)
                    view.onGetSearchTownFailure(response.body()!!.message!!)
                view.onGetSearchTownSuccess(response.body()!!.result!!)
            }

            override fun onFailure(
                call: Call<BaseResponse<ArrayList<FindMyTownResponse>>>,
                t: Throwable
            ) {
                view.onGetSearchTownFailure(t.message?:"동네 검색 api 에러")
            }

        })
    }
}