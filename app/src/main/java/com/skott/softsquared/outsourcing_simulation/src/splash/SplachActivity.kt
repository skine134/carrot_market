package com.skott.softsquared.outsourcing_simulation.src.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.SplasherLayoutBinding
import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass.Companion.sSharedPreferences
import com.skott.softsquared.outsourcing_simulation.src.config.BaseActivity
import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.certification.CertificationActivity
import com.skott.softsquared.outsourcing_simulation.src.main.certification.models.MobileCheckRequest
import com.skott.softsquared.outsourcing_simulation.src.main.certification.models.SignInResponse
import com.skott.softsquared.outsourcing_simulation.src.main.home.HomeActivity
import com.skott.softsquared.outsourcing_simulation.src.splash.model.AutoSignInResponse

class SplashActivity : BaseActivity<SplasherLayoutBinding>(SplasherLayoutBinding::inflate)
    ,AutoSignInInView {
    private lateinit var context: Context
    private lateinit var autoSignInService: AutoSignInService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        autoSignInService = AutoSignInService(this)
        Handler(Looper.getMainLooper()).postDelayed({

            if (!sSharedPreferences.getString(this.getString(R.string.jwt_key), "").equals(""))
            {
                val phone = sSharedPreferences.getString(this.getString(R.string.phone_number_key),"")!!
                Log.d("phone_number",phone)
                autoSignInService.tryGetAutoSignIn()
                return@postDelayed
            }
            val nextActivity = CertificationActivity::class.java
            val intent = Intent(this, nextActivity)
            startActivity(intent)
            finish()
        }, 1000)
    }

    override fun onAutoSignInSuccess(autoSignInResponseArray: ArrayList<AutoSignInResponse>) {
        val nextActivity = HomeActivity::class.java
        val intent = Intent(this, nextActivity)
        showCustomToast("자동 로그인 성공")
        ApplicationClass.userTownInfoArray = autoSignInResponseArray
        startActivity(intent)
        finish()
    }

    override fun onAutoSignInFailure(message: String) {
        showCustomToast(message)
    }

}