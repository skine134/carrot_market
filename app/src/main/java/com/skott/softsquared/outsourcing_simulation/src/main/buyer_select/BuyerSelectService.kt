package com.skott.softsquared.outsourcing_simulation.src.main.buyer_select

import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.buyer_select.model.PostBuyerRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BuyerSelectService(val view:BuyerSelectActivity) {
    fun tryPostBuyerSelect(postBuyerRequest: PostBuyerRequest)
    {
        val api = ApplicationClass.sRetrofit.create(BuyerSelectRetrofitInterface::class.java)
        api.postBuyerSelect(postBuyerRequest).enqueue(object: Callback<BaseResponse<String>> {
            override fun onResponse(
                call: Call<BaseResponse<String>>,
                response: Response<BaseResponse<String>>
            ) {
                if(response.body()!!.code!=1000){
                    view.onBuyerSelectFailure(response.body()!!.message!!)
                    return
                }
                view.onBuyerSelectSuccess()
            }

            override fun onFailure(call: Call<BaseResponse<String>>, t: Throwable) {
                view.onBuyerSelectFailure(t.message?:"구매자 등록 에러")
            }


        })
    }
}