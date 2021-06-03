package com.skott.softsquared.outsourcing_simulation.src.main.sold_product_list

import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list.SaleProductListRetrofitInterface
import com.skott.softsquared.outsourcing_simulation.src.main.sold_product_list.model.BuyerListResponse
import com.skott.softsquared.outsourcing_simulation.src.main.sold_product_list.model.SoldProductListResponse
import kotlinx.serialization.Serializable
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

    fun tryPatchSale(itemIndex:Int)
    {

        val api = ApplicationClass.sRetrofit.create(SoldProductListRetrofitInterface::class.java)
        api.patchSale(itemIndex).enqueue(object :Callback<BaseResponse<String>>{
            override fun onResponse(
                call: Call<BaseResponse<String>>,
                response: Response<BaseResponse<String>>
            ) {
                if(response.body()!!.code!=1000)
                {
                    view.onPatchSaleFailure(response.body()!!.message!!)
                    return
                }
                view.onPatchSaleSuccess()
            }

            override fun onFailure(call: Call<BaseResponse<String>>, t: Throwable) {
                view.onPatchSaleFailure(t.message?:"sale product list error")
            }

        })
    }
    fun tryGetBuyerList(itemIndex:Int)
    {

        val api = ApplicationClass.sRetrofit.create(SoldProductListRetrofitInterface::class.java)
        api.getBuyerList(itemIndex).enqueue(object :Callback<BaseResponse<ArrayList<BuyerListResponse>>>{
            override fun onResponse(
                call: Call<BaseResponse<ArrayList<BuyerListResponse>>>,
                response: Response<BaseResponse<ArrayList<BuyerListResponse>>>
            ) {
                if(response.body()!!.code!=1000)
                {
                    view.onGetBuyerResponseFailure(response.body()!!.message!!)
                    return
                }
                view.onGetBuyerResponseSuccess(response.body()!!.result!!)
            }

            override fun onFailure(call: Call<BaseResponse<ArrayList<BuyerListResponse>>>, t: Throwable) {
                view.onPatchSaleFailure(t.message?:"sold buyer user api error")
            }

        })
    }
}