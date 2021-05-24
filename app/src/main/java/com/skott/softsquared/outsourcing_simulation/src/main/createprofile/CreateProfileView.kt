package com.skott.softsquared.outsourcing_simulation.src.main.createprofile

import com.skott.softsquared.outsourcing_simulation.src.main.createprofile.models.SignupResponse

interface CreateProfileView {
    fun jwtListener(signupResponse: SignupResponse)
    fun signUpErrorListener(message:String)
}