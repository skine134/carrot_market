package com.skott.softsquared.outsourcing_simulation.src.main.my_town_setting

import com.skott.softsquared.outsourcing_simulation.src.main.my_town_setting.model.MyTownSettingResponse

interface MyTownSettingView {

    fun onGetMyTownSuccess(myTownSettingResponseArray:ArrayList<MyTownSettingResponse>)
    fun onGetMyTownFailure(message:String)

    fun onPatchMyTownSuccess()
    fun onPatchMyTownFailure(message:String)

    fun onPatchDeleteMyTownSuccess()
    fun onPatchDeleteMyTownFailure(message:String)

    fun onPatchRangeUpdateSuccess()
    fun onPatchRangeUpdateFailure(message:String)
}