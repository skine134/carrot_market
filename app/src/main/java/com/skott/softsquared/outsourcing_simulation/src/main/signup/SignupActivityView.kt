package com.skott.softsquared.outsourcing_simulation.src.main.signup

import com.skott.softsquared.outsourcing_simulation.src.main.signup.models.SignUpResponse

interface SignupActivityView {
    fun onCertificationsSuccess(response:SignUpResponse)
    fun onCertificationsFailure(message:String)
}