package com.skott.softsquared.outsourcing_simulation.src.main.find_town

import com.skott.softsquared.outsourcing_simulation.src.main.find_town.model.FindMyTownResponse

interface FindMyTownView {
    fun onGetSearchTownSuccess(findMyTownResponseArray:ArrayList<FindMyTownResponse>)
    fun onGetSearchTownFailure(message:String)
    fun onPostRegisterAddressSuccess()
    fun onPostRegisterAddressFailure(message:String)
    fun onPatchDeleteAddressSuccess()
    fun onPatchDeleteAddressFailure(message:String)
}