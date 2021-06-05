package com.skott.softsquared.outsourcing_simulation.src.main.home_my_carrot

import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.home_my_carrot.model.MyCarrotResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyCarrotService(val view:MyCarrotFragment) {
    fun tryGetMyCarrot()
    {
        val api = ApplicationClass.sRetrofit.create(MyCarrotRetrofitInterface::class.java)
        api.getMyCarrot().enqueue(object: Callback<BaseResponse<MyCarrotResponse>>{
            override fun onResponse(
                call: Call<BaseResponse<MyCarrotResponse>>,
                response: Response<BaseResponse<MyCarrotResponse>>
            ) {
                if(response.body()!!.code!=1000)
                    view.onGetMyCarrotFailure(response.body()!!.message!!)
                view.onGetMyCarrotSuccess(response.body()!!.result!!)
            }

            override fun onFailure(call: Call<BaseResponse<MyCarrotResponse>>, t: Throwable) {
                view.onGetMyCarrotFailure(t.message?:"에러 발생")
            }

        })
    }
}