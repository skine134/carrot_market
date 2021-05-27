package com.skott.softsquared.outsourcing_simulation.src.splash

import com.skott.softsquared.outsourcing_simulation.src.main.signin.models.SignInResponse
import com.skott.softsquared.outsourcing_simulation.src.main.signup.models.SignUpResponse

interface SplashActivityView {

    fun onSignInSuccess(signInResponse: SignInResponse)
    fun onSignInFailure(message:String)
}