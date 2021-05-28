package com.skott.softsquared.outsourcing_simulation.src.main.certification

import com.skott.softsquared.outsourcing_simulation.src.main.certification.models.CertificationResponse
import com.skott.softsquared.outsourcing_simulation.src.main.certification.models.SignInResponse

interface CertificationActivityView {
    fun onSignUpMobileCheckSuccess()
    fun onSignUpMobileCheckFailure(message:String)
    fun onSignInSuccess(signInResponse: SignInResponse)
    fun onSignInFailure(message:String)
    fun onCertificationsSuccess(response: CertificationResponse)
    fun onCertificationsFailure(message:String)
}