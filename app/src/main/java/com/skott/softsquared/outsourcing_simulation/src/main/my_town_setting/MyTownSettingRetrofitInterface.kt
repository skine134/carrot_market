package com.skott.softsquared.outsourcing_simulation.src.main.my_town_setting

import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.my_town_setting.model.ChangeMyTownRequest
import com.skott.softsquared.outsourcing_simulation.src.main.my_town_setting.model.MyTownSettingResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

interface MyTownSettingRetrofitInterface
{
    @GET("/app/locations/my-villages")
    fun getMyTown(): Call<BaseResponse<ArrayList<MyTownSettingResponse>>>

    @PATCH("/app/locations/current-village")
    fun patchMyTown(@Body changeMyTownRequest: ChangeMyTownRequest):Call<BaseResponse<String>>

    @PATCH("/app/locations/my-villages")
    fun patchDeleteMyTown(@Body changeMyTownRequest: ChangeMyTownRequest):Call<BaseResponse<String>>
}