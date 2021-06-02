package com.skott.softsquared.outsourcing_simulation.src.main.collect_user_management

import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.collect_user_management.model.CollectUserManagementResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CollectUserManagementRetrofitInterface {
    @GET("/app/likes/seller-list")
    fun getCollectUserList(): Call<BaseResponse<ArrayList<CollectUserManagementResponse>>>

    @POST("/app/likes/sellers/{sellerIdx}")
    fun postCollectSeller(
        @Path("sellerIdx") userIdx:Int
    ): Call<BaseResponse<String>>
}