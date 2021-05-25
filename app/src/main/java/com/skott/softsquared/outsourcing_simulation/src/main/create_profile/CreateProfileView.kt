package com.skott.softsquared.outsourcing_simulation.src.main.create_profile

import com.skott.softsquared.outsourcing_simulation.src.main.create_profile.models.SignupResponse
import com.skott.softsquared.outsourcing_simulation.src.main.signin.models.SigninResponse

interface CreateProfileView {
    fun onSignInSuccess(signinResponse: SigninResponse)
    fun onSignInFailure(message:String)
    fun onSignUpSuccess(signupResponse: SignupResponse)
    fun onSignUpFailure(message:String)
}