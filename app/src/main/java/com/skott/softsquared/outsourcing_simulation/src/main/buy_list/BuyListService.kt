package com.skott.softsquared.outsourcing_simulation.src.main.buy_list

import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.buy_list.model.BuyListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BuyListService(val view: BuyListActivity) {
    fun tryGetBuyList()
    {
        val api = ApplicationClass.sRetrofit.create(BuyListRetrofitInterface::class.java)
        api.getBuyList().enqueue(object : Callback<BaseResponse<ArrayList<BuyListResponse>>>{
            override fun onResponse(
                call: Call<BaseResponse<ArrayList<BuyListResponse>>>,
                response: Response<BaseResponse<ArrayList<BuyListResponse>>>
            ) {
                if(response.body()!!.code!=1000)
                {
                    view.onGetBuyListFailure(response.body()!!.message!!)
                    return
                }
                view.onGetBuyListSuccess(response.body()!!.result!!)
            }

            override fun onFailure(
                call: Call<BaseResponse<ArrayList<BuyListResponse>>>,
                t: Throwable
            ) {
                view.onGetBuyListFailure(t.message?:"구매 내역 조회 api 에러")
            }

        })
    }
}