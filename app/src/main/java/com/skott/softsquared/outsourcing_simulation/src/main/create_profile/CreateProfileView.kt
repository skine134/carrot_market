package com.skott.softsquared.outsourcing_simulation.src.main.create_profile

import com.skott.softsquared.outsourcing_simulation.src.main.create_profile.models.SignupResponse

interface CreateProfileView {
    fun onSignUpSuccess(signupResponse: SignupResponse)
    fun onSignUpFailure(message:String)

    fun onPostRegisterAddressSuccess()
    fun onPostRegisterAddressFailure(message:String)
}