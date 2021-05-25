package com.skott.softsquared.outsourcing_simulation.src.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.skott.config.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.skott.config.ApplicationClass.Companion.sSharedPreferences
import com.skott.softsquared.outsourcing_simulation.src.main.signup.SignupActivity
import com.skott.config.BaseActivity
import com.skott.softsquared.outsourcing_simulation.databinding.SplasherLayoutBinding
import com.skott.softsquared.outsourcing_simulation.src.main.MainActivity

class SplashActivity : BaseActivity<SplasherLayoutBinding>(SplasherLayoutBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sSharedPreferences = applicationContext.getSharedPreferences("carrot_market", MODE_PRIVATE)
        Handler(Looper.getMainLooper()).postDelayed({
//            val nextActivity = if(sSharedPreferences.getString(X_ACCESS_TOKEN,"").equals("")) SignupActivity::class.java else MainActivity::class.java
            val nextActivity = MainActivity::class.java
            startActivity(Intent(this, nextActivity))
            finish()
        }, 1500)
    }
}