package com.skott.softsquared.outsourcing_simulation.src.main.home_product_list

import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.home_product_list.model.ProductListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductListService(val view: ProductListFragment) {
    fun tryGetProductList(
        villageIdx: Int,
        rangeLevel: Int,
        categories: String,
        lastItemIdx: Int,
        numOfPages: Int = 15

    ) {
        val api = ApplicationClass.sRetrofit.create(ProductListRetrofitInterface::class.java)
        api.getItems(
            villageIdx = villageIdx,
            rangeLevel = rangeLevel,
            categories = categories,
            lastItemIdx = lastItemIdx,
            numOfPages = numOfPages
        ).enqueue(object : Callback<BaseResponse<ArrayList<ProductListResponse>>> {
            override fun onResponse(
                call: Call<BaseResponse<ArrayList<ProductListResponse>>>,
                response: Response<BaseResponse<ArrayList<ProductListResponse>>>
            ) {
                if(response.body()!!.code!=1000) {
                    view.onProductListFailure(response.body()!!.message!!)
                    return
                }
                view.onProductListSuccess(response.body()!!.result!!)
            }

            override fun onFailure(
                call: Call<BaseResponse<ArrayList<ProductListResponse>>>,
                t: Throwable
            ) {
                view.onProductListFailure(t.message?:"상품 리스트 오류 발생")
            }

        })
    }
}