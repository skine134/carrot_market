package com.skott.softsquared.outsourcing_simulation.src.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.SplasherLayoutBinding
import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass.Companion.sSharedPreferences
import com.skott.softsquared.outsourcing_simulation.src.config.BaseActivity
import com.skott.softsquared.outsourcing_simulation.src.main.home.HomeActivity
import com.skott.softsquared.outsourcing_simulation.src.main.signin.models.SignInRequest
import com.skott.softsquared.outsourcing_simulation.src.main.signin.models.SignInResponse
import com.skott.softsquared.outsourcing_simulation.src.main.signup.SignupActivity

class SplashActivity : BaseActivity<SplasherLayoutBinding>(SplasherLayoutBinding::inflate),
    SplashActivityView {
    private lateinit var context: Context
    private lateinit var signInService: SignInService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        signInService = SignInService(this)
        Handler(Looper.getMainLooper()).postDelayed({

            if (!sSharedPreferences.getString(this.getString(R.string.jwt_key), "").equals(""))
            {
                signInService.tryGetJwt(SignInRequest(sSharedPreferences.getString(this.getString(R.string.phone_number_key),"")!!))
                return@postDelayed
            }
            val nextActivity = SignupActivity::class.java
            val intent = Intent(this, nextActivity)
            startActivity(intent)
            finish()
        }, 1500)
    }

    override fun onSignInSuccess(signInResponse: SignInResponse) {
        val editor = sSharedPreferences.edit()
        editor.putString(context.getString(R.string.jwt_key), signInResponse.jwt)
        editor.apply()
        val nextActivity = HomeActivity::class.java
        val intent = Intent(this, nextActivity)
        startActivity(intent)
        finish()
    }

    override fun onSignInFailure(message: String) {
        showCustomToast(message)
    }

}