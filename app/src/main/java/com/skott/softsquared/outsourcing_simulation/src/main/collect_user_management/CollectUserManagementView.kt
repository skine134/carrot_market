package com.skott.softsquared.outsourcing_simulation.src.main.collect_user_management

import com.skott.softsquared.outsourcing_simulation.src.main.collect_user_management.model.CollectUserManagementResponse

interface CollectUserManagementView {
    fun onGetCollectUserListSuccess(collectUserManagementResponseArray:ArrayList<CollectUserManagementResponse>)
    fun onGetCollectUserListFailure(message:String)
    fun onPostCollectUserSuccess()
    fun onPostCollectUserFailure(message:String)
}