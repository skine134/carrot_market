package com.skott.softsquared.outsourcing_simulation.src.main.collect_user

import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.collect_user.model.CollectUserResponse
import com.skott.softsquared.outsourcing_simulation.src.main.favorite_product_list.model.FavoriteItemResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CollectUserService(val view:CollectUserActivity) {
    fun tryGetCollectUser()
    {
        val api=ApplicationClass.sRetrofit.create(CollectUserRetrofitInterface::class.java)
        api.getCollectUser().enqueue(object: Callback<BaseResponse<ArrayList<CollectUserResponse>>>
        {
            override fun onResponse(
                call: Call<BaseResponse<ArrayList<CollectUserResponse>>>,
                response: Response<BaseResponse<ArrayList<CollectUserResponse>>>
            ) {
                if(response.body()!!.code!=1000)
                {
                    view.onGetCollectUserFailure(response.body()!!.message!!)
                    return
                }
                view.onGetCollectUserSuccess(response.body()!!.result!!)
            }
            override fun onFailure(
                call: Call<BaseResponse<ArrayList<CollectUserResponse>>>,
                t: Throwable
            ) {
            view.onGetCollectUserFailure(t.message?:"Get Collect User error")
            }

        })
    }
}