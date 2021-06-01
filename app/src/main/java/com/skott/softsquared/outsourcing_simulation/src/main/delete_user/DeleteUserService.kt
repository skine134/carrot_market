package com.skott.softsquared.outsourcing_simulation.src.main.delete_user

import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeleteUserService(val view:DeleteUserActivity){
    fun tryPatchDeleteUser()
    {
        val api = ApplicationClass.sRetrofit.create(DeleteUserRetrofitInterface::class.java)
        api.patchDeleteUser().enqueue(object: Callback<BaseResponse<String>> {
            override fun onResponse(
                call: Call<BaseResponse<String>>,
                response: Response<BaseResponse<String>>
            ) {

                if(response.body()!!.code!=1000)
                {
                    view.onPatchDeleteUserFailure(response.body()!!.message!!)
                    return
                }
                view.onPatchDeleteUserSuccess()
            }

            override fun onFailure(call: Call<BaseResponse<String>>, t: Throwable) {
                view.onPatchDeleteUserFailure(t.message?:"회원 탈퇴 api 오류")
            }
        })
    }
}