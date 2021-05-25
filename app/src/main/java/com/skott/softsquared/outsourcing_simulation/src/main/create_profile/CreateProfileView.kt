package com.skott.softsquared.outsourcing_simulation.src.main.create_profile

import com.skott.softsquared.outsourcing_simulation.src.main.create_profile.models.SignupResponse

interface CreateProfileView {
    fun jwtListener(signupResponse: SignupResponse)
    fun signUpErrorListener(message:String)
}