package com.skott.softsquared.outsourcing_simulation.src.main.signup

import com.skott.softsquared.outsourcing_simulation.src.main.signup.models.CertificationsResponse

interface SignupActivityView {
    fun certificationsResponseListener(response:CertificationsResponse)
    fun certificationsResponseErrorListener(message:String)
}