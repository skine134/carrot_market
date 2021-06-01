package com.skott.softsquared.outsourcing_simulation.src.main.sold_product_list

import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.sold_product_list.model.SoldProductListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SoldProductListService(val view:SoldProductListFragment) {
    fun tryGetSoldProductList()
    {
        val api = ApplicationClass.sRetrofit.create(SoldProductListRetrofitInterface::class.java)
        api.getSoldProductList().enqueue(object:
            Callback<BaseResponse<ArrayList<SoldProductListResponse>>> {
            override fun onResponse(
                call: Call<BaseResponse<ArrayList<SoldProductListResponse>>>,
                response: Response<BaseResponse<ArrayList<SoldProductListResponse>>>
            ) {
                if(response.body()!!.code!=1000)
                {
                    view.onGetSoldProductListViewFailure(response.body()!!.message!!)
                    return
                }
                view.onGetSoldProductListViewSuccess(response.body()!!.result!!)
            }

            override fun onFailure(
                call: Call<BaseResponse<ArrayList<SoldProductListResponse>>>,
                t: Throwable
            ) {
                view.onGetSoldProductListViewFailure(t.message?:"sale product list error")
            }

        })
    }
}