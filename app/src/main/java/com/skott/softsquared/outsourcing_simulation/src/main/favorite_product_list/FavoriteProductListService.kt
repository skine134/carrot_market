package com.skott.softsquared.outsourcing_simulation.src.main.favorite_product_list

import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.favorite_product_list.model.FavoriteItemResponse
import com.skott.softsquared.outsourcing_simulation.src.main.product_detail.ProductDetailRetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoriteProductListService(val view: FavoriteProductListFragment) {
    fun tryGetFavoriteList()
    {
        val api = ApplicationClass.sRetrofit.create(FavoriteProductListRetrofitInterface::class.java)
        api.getFavoriteItems().enqueue(object: Callback<BaseResponse<ArrayList<FavoriteItemResponse>>>{
            override fun onResponse(
                call: Call<BaseResponse<ArrayList<FavoriteItemResponse>>>,
                response: Response<BaseResponse<ArrayList<FavoriteItemResponse>>>
            ) {
                if(response.body()!!.code!=1000)
                {
                    view.onGetFavoriteListViewFailure(response.body()!!.message!!)
                    return
                }
                view.onGetFavoriteListViewSuccess(response.body()!!.result!!)
            }

            override fun onFailure(call: Call<BaseResponse<ArrayList<FavoriteItemResponse>>>, t: Throwable) {
                view.onGetFavoriteListViewFailure(t.message?:"favorite product list error")
            }

        })
    }

    fun tryGetFavoriteProduct(itemIdx:Int)
    {
        val api = ApplicationClass.sRetrofit.create(ProductDetailRetrofitInterface::class.java)
        api.getFavoriteProduct(itemIdx).enqueue(object:
            Callback<BaseResponse<String>> {
            override fun onResponse(
                call: Call<BaseResponse<String>>,
                response: Response<BaseResponse<String>>
            ) {
                if(response.body()!!.code!=1000)
                {
                    view.onGetFavoriteProductFailure(response.body()!!.message?:"상세 정보 api 에러 발생.")
                    return
                }
                view.onGetFavoriteProductSuccess()
            }

            override fun onFailure(call: Call<BaseResponse<String>>, t: Throwable) {
                view.onGetFavoriteProductFailure(t.message?:"관심 목록 api 에러 발생.")
            }

        })
    }
}