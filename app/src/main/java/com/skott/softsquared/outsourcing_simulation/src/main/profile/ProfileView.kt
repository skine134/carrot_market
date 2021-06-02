package com.skott.softsquared.outsourcing_simulation.src.main.profile

import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.main.profile.model.ProfileResponse

interface ProfileView {
    fun onGetProfileSuccess(profileResponse: ProfileResponse)
    fun onGetProfileFailure(message:String)
    fun onPostCollectUserSuccess()
    fun onPostCollectUserFailure(message:String)
}