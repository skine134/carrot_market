package com.skott.softsquared.outsourcing_simulation.src.main.signin

import com.skott.softsquared.outsourcing_simulation.src.main.signin.models.SigninResponse
import com.skott.softsquared.outsourcing_simulation.src.main.signup.models.SignUpResponse

interface SigninActivityView {

    //PostJwtListener
    fun jwtListener(signinResponse: SigninResponse)
    fun jwtErrorListener(message:String)
    fun certificationsResponseListener(response: SignUpResponse)
    fun certificationsResponseErrorListener(message:String)
}