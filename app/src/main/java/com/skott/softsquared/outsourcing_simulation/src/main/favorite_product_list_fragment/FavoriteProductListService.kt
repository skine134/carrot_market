package com.skott.softsquared.outsourcing_simulation.src.main.favorite_product_list_fragment

import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.favorite_product_list_fragment.model.FavoriteItemModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoriteProductListService(val view: FavoriteProductListFragment) {
    fun tryGetFavoriteList()
    {
        val api = ApplicationClass.sRetrofit.create(FavoriteProductListRetrofitInterface::class.java)
        api.getFavoriteItems().enqueue(object: Callback<BaseResponse<ArrayList<FavoriteItemModel>>>{
            override fun onResponse(
                call: Call<BaseResponse<ArrayList<FavoriteItemModel>>>,
                response: Response<BaseResponse<ArrayList<FavoriteItemModel>>>
            ) {
                if(response.body()!!.code!=1000)
                {
                    view.onGetFavoriteListViewFailure(response.body()!!.message!!)
                    return
                }
                view.onGetFavoriteListViewSuccess(response.body()!!.result!!)
            }

            override fun onFailure(call: Call<BaseResponse<ArrayList<FavoriteItemModel>>>, t: Throwable) {
                view.onGetFavoriteListViewFailure(t.message?:"favorite product list error")
            }

        })
    }
}