package com.skott.softsquared.outsourcing_simulation.src.main.signin

import com.skott.softsquared.outsourcing_simulation.src.main.signin.models.SigninResponse
import com.skott.softsquared.outsourcing_simulation.src.main.signup.models.CertificationsResponse

interface SigninActivityView {
    fun jwtListener(signinResponse: SigninResponse)
    fun jwtErrorListener(message:String)
    fun certificationsResponseListener(response: CertificationsResponse)
    fun certificationsResponseErrorListener(message:String)
}