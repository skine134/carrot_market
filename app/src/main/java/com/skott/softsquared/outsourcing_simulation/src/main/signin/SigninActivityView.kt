package com.skott.softsquared.outsourcing_simulation.src.main.signin

import com.skott.softsquared.outsourcing_simulation.src.main.signin.models.SigninResponse

interface SigninActivityView {
    fun jwtListener(signinResponse: SigninResponse)
    fun jwtErrorListener(messae:String)
}