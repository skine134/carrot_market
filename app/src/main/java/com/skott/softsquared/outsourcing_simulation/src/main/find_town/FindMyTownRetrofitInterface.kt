package com.skott.softsquared.outsourcing_simulation.src.main.find_town

import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.find_town.model.FindMyTownResponse
import com.skott.softsquared.outsourcing_simulation.src.main.find_town.model.RegisterAddressRequest
import com.skott.softsquared.outsourcing_simulation.src.main.my_town_setting.model.ChangeMyTownRequest
import retrofit2.Call
import retrofit2.http.*

interface FindMyTownRetrofitInterface {
    @GET("/app/locations/search")
    fun getSearchTown(
        @Query("searchWord") searchWord: String
    ): Call<BaseResponse<ArrayList<FindMyTownResponse>>>

    @POST("/app/locations/my-villages")
    fun postRegisterAddress(@Body registerAddressRequest: RegisterAddressRequest): Call<BaseResponse<String>>

    @PATCH("/app/locations/my-villages")
    fun patchDeleteMyTown(@Body registerAddressRequest: RegisterAddressRequest):Call<BaseResponse<String>>
}