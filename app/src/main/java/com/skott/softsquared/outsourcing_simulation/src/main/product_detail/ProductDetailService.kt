package com.skott.softsquared.outsourcing_simulation.src.main.product_detail

import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.product_detail.model.ProductDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetailService(val view:ProductDetailActivity){
    fun tryGetProductDetail(itemIdx:Int)
    {
        val api = ApplicationClass.sRetrofit.create(ProductDetailRetrofitInterface::class.java)
        api.getProductDetail(itemIdx).enqueue(object:
            Callback<BaseResponse<ProductDetailResponse>> {
            override fun onResponse(
                call: Call<BaseResponse<ProductDetailResponse>>,
                response: Response<BaseResponse<ProductDetailResponse>>
            ) {
                if(response.body()!!.code!=1000)
                {
                    view.onGetProductDetailFailure(response.body()!!.message?:"상세 정보 api 에러 발생.")
                    return
                }
                view.onGetProductDetailSuccess(response.body()!!.result!!)
            }

            override fun onFailure(call: Call<BaseResponse<ProductDetailResponse>>, t: Throwable) {
                view.onGetProductDetailFailure(t.message?:"상세 정보 api 에러 발생.")
            }

        })
    }
}