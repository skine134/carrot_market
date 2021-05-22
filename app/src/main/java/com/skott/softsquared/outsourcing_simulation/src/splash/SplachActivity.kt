package com.skott.softsquared.outsourcing_simulation.src.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.skott.softsquared.outsourcing_simulation.src.main.SignInActivity
import com.skott.config.config.BaseActivity
import com.skott.softsquared.outsourcing_simulation.databinding.SplasherLayoutBinding

class SplashActivity : BaseActivity<SplasherLayoutBinding>(SplasherLayoutBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }, 1500)
    }
}