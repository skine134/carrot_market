package com.skott.softsquared.outsourcing_simulation.src.main.signin

import com.skott.softsquared.outsourcing_simulation.src.main.signin.models.SignInResponse
import com.skott.softsquared.outsourcing_simulation.src.main.signup.models.SignUpResponse

interface SigninActivityView {

    fun onSignInSuccess(signInResponse: SignInResponse)
    fun onSignInFailure(message:String)
    fun onCertificationsSuccess(response: SignUpResponse)
    fun onCertificationsFailure(message:String)
}