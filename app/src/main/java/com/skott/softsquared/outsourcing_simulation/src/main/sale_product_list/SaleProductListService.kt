package com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list

import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.favorite_product_list.FavoriteProductListFragment
import com.skott.softsquared.outsourcing_simulation.src.main.favorite_product_list.FavoriteProductListRetrofitInterface
import com.skott.softsquared.outsourcing_simulation.src.main.favorite_product_list.model.FavoriteItemResponse
import com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list.model.SaleProductListResponse
import com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list.model.SoldOutRequest
import com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list.model.SoldResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SaleProductListService(val view: SaleProductListFragment) {
    fun tryGetSaleProductList()
    {
        val api = ApplicationClass.sRetrofit.create(SaleProductListRetrofitInterface::class.java)
        api.getSaleProductList().enqueue(object:
            Callback<BaseResponse<ArrayList<SaleProductListResponse>>> {
            override fun onResponse(
                call: Call<BaseResponse<ArrayList<SaleProductListResponse>>>,
                response: Response<BaseResponse<ArrayList<SaleProductListResponse>>>
            ) {
                if(response.body()!!.code!=1000)
                {
                    view.onGetSaleProductListViewFailure(response.body()!!.message!!)
                    return
                }
                view.onGetSaleProductListViewSuccess(response.body()!!.result!!)
            }

            override fun onFailure(call: Call<BaseResponse<ArrayList<SaleProductListResponse>>>, t: Throwable) {
                view.onGetSaleProductListViewFailure(t.message?:"sale product list error")
            }

        })
    }
    fun tryPostSoldOut(soldOutRequest: SoldOutRequest)
    {

        val api = ApplicationClass.sRetrofit.create(SaleProductListRetrofitInterface::class.java)
        api.postSoldOut(soldOutRequest).enqueue(object:
            Callback<BaseResponse<ArrayList<SoldResponse>>> {
            override fun onResponse(
                call: Call<BaseResponse<ArrayList<SoldResponse>>>,
                response: Response<BaseResponse<ArrayList<SoldResponse>>>
            ) {
                if(response.body()!!.code!=1000)
                {
                    view.onPostSoldOutFailure(response.body()!!.message!!)
                    return
                }
                view.onPostSoldOutSuccess(response.body()!!.result!!)
            }

            override fun onFailure(call: Call<BaseResponse<ArrayList<SoldResponse>>>, t: Throwable) {
                view.onPostSoldOutFailure(t.message?:"sale product list error")
            }

        })
    }
}