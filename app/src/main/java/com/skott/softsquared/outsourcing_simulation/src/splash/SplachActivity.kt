package com.skott.softsquared.outsourcing_simulation.src.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.skott.softsquared.outsourcing_simulation.databinding.SplasherLayoutBinding
import com.skott.softsquared.outsourcing_simulation.src.config.BaseActivity
import com.skott.softsquared.outsourcing_simulation.src.main.certification.CertificationActivity

class SplashActivity : BaseActivity<SplasherLayoutBinding>(SplasherLayoutBinding::inflate)
     {
    private lateinit var context: Context
    private lateinit var signInService: SignInService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        signInService = SignInService(this)
        Handler(Looper.getMainLooper()).postDelayed({

//            if (!sSharedPreferences.getString(this.getString(R.string.jwt_key), "").equals(""))
//            {
//                val phone = sSharedPreferences.getString(this.getString(R.string.phone_number_key),"")!!
//                Log.d("phone_number",phone)
//                signInService.tryGetJwt(MobileCheckRequest(phone))
//                return@postDelayed
//            }
            val nextActivity = CertificationActivity::class.java
            val intent = Intent(this, nextActivity)
            startActivity(intent)
            finish()
        }, 1000)
    }

//    override fun onSignInSuccess(signInResponse: SignInResponse) {
//        val editor = sSharedPreferences.edit()
//        editor.putString(context.getString(R.string.jwt_key), signInResponse.jwt)
//        editor.apply()
//        val nextActivity = HomeActivity::class.java
//        val intent = Intent(this, nextActivity)
//        showCustomToast("로그인 성공")
//        startActivity(intent)
//        finish()
//    }
//
//    override fun onSignInFailure(message: String) {
//        showCustomToast(message)
//    }

}