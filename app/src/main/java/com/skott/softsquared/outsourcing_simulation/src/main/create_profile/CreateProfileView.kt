package com.skott.softsquared.outsourcing_simulation.src.main.create_profile

import com.skott.softsquared.outsourcing_simulation.src.main.create_profile.models.SignupResponse
import com.skott.softsquared.outsourcing_simulation.src.main.signin.models.SigninResponse

interface CreateProfileView {
    fun jwtListener(signinResponse: SigninResponse)
    fun signUpErrorListener(message:String)
    fun signupResponseListener(signupResponse: SignupResponse)
    fun signInErrorListener(message:String)
}