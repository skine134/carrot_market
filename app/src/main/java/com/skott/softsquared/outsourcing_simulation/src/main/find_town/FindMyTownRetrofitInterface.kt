package com.skott.softsquared.outsourcing_simulation.src.main.find_town

import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.find_town.model.FindMyTownResponse
import com.skott.softsquared.outsourcing_simulation.src.main.find_town.model.RegisterAddressRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface FindMyTownRetrofitInterface {
    @GET("/app/locations/search")
    fun getSearchTown(
        @Query("searchWord") searchWord: String
    ): Call<BaseResponse<ArrayList<FindMyTownResponse>>>

    @POST("/app/locations/my-villages")
    fun postRegisterAddress(@Body registerAddressRequest: RegisterAddressRequest): Call<BaseResponse<String>>
}