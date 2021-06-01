package com.skott.softsquared.outsourcing_simulation.src.main.seek_town

import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.seek_town.model.TownResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SeekTownService(val view:SeekTownFragment) {
    fun tryGetTowns()
    {
        val api = ApplicationClass.sRetrofit.create(SeekTownRetrofitInterface::class.java)
        api.getTowns().enqueue(object: Callback<BaseResponse<TownResponse>> {
            override fun onResponse(
                call: Call<BaseResponse<TownResponse>>,
                response: Response<BaseResponse<TownResponse>>
            ) {
                if(response.body()!!.code!=100)
                    view.onGetTownsFailure(response.body()!!.message!!)
                view.onGetTownsSuccess(response.body()!!.result!!)
            }

            override fun onFailure(call: Call<BaseResponse<TownResponse>>, t: Throwable) {
                view.onGetTownsFailure(t.message?:"동네 범위 api 에러")
            }

        })
    }
}