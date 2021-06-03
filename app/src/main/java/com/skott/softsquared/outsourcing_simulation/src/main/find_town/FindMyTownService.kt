package com.skott.softsquared.outsourcing_simulation.src.main.find_town

import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.find_town.model.FindMyTownResponse
import com.skott.softsquared.outsourcing_simulation.src.main.find_town.model.RegisterAddressRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FindMyTownService(val view:FindTownActivity) {
    fun tryGetTown(searchWord:String)
    {
        val api= ApplicationClass.sRetrofit.create(FindMyTownRetrofitInterface::class.java)
        api.getSearchTown(searchWord).enqueue(object:
            Callback<BaseResponse<ArrayList<FindMyTownResponse>>> {
            override fun onResponse(
                call: Call<BaseResponse<ArrayList<FindMyTownResponse>>>,
                response: Response<BaseResponse<ArrayList<FindMyTownResponse>>>
            ) {
                if(response.body()!!.code!=1000) {
                    view.onGetSearchTownFailure(response.body()!!.message!!)
                    return
                }
                view.onGetSearchTownSuccess(response.body()!!.result!!)
            }
            override fun onFailure(
                call: Call<BaseResponse<ArrayList<FindMyTownResponse>>>,
                t: Throwable
            ) {
                view.onGetSearchTownFailure(t.message?:"동네 검색 api 에러")
            }

        })
    }
    fun tryPostRegisterAddress(registerAddressRequest: RegisterAddressRequest)
    {
        val api = ApplicationClass.sRetrofit.create(FindMyTownRetrofitInterface::class.java)
        api.postRegisterAddress(registerAddressRequest).enqueue(object:Callback<BaseResponse<String>>{
            override fun onResponse(
                call: Call<BaseResponse<String>>,
                response: Response<BaseResponse<String>>
            ) {
                if(response.body()!!.code!=1000) {
                    view.onPostRegisterAddressFailure(response.body()!!.message!!)
                    return
                }
                view.onPostRegisterAddressSuccess()
            }

            override fun onFailure(call: Call<BaseResponse<String>>, t: Throwable) {
                view.onPostRegisterAddressFailure(t.message?:"동네 등록 api 에러")
            }

        })
    }
    fun tryPatchDeleteAddress(registerAddressRequest: RegisterAddressRequest)
    {
        val api = ApplicationClass.sRetrofit.create(FindMyTownRetrofitInterface::class.java)
        api.patchDeleteMyTown(registerAddressRequest).enqueue(object:Callback<BaseResponse<String>>{
            override fun onResponse(
                call: Call<BaseResponse<String>>,
                response: Response<BaseResponse<String>>
            ) {
                if(response.body()!!.code!=1000) {
                    view.onPostRegisterAddressFailure(response.body()!!.message!!)
                    return
                }
                view.onPatchDeleteAddressSuccess()
            }

            override fun onFailure(call: Call<BaseResponse<String>>, t: Throwable) {
                view.onPostRegisterAddressFailure(t.message?:"동네 삭제 api 에러")
            }

        })
    }
}