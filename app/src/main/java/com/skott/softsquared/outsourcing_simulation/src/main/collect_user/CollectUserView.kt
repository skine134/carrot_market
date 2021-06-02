package com.skott.softsquared.outsourcing_simulation.src.main.collect_user

import com.skott.softsquared.outsourcing_simulation.src.main.collect_user.model.CollectUserResponse

interface CollectUserView {
    fun onGetCollectUserSuccess(collectUserResponseArray:ArrayList<CollectUserResponse>)
    fun onGetCollectUserFailure(message:String)
}