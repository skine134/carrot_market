package com.skott.softsquared.outsourcing_simulation.src.splash

import com.skott.softsquared.outsourcing_simulation.src.splash.model.AutoSignInResponse

interface AutoSignInInView {

    fun onAutoSignInSuccess(autoSignInResponseArray: ArrayList<AutoSignInResponse>)
    fun onAutoSignInFailure(message:String)
}