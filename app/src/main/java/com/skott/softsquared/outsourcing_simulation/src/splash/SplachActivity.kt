package com.skott.softsquared.outsourcing_simulation.src.splash

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
import com.skott.softsquared.outsourcing_simulation.src.main.signup.SignupActivity

class SplashActivity : BaseActivity<SplasherLayoutBinding>(SplasherLayoutBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sSharedPreferences.edit().remove(this.getString(R.string.jwt_key))
//        sSharedPreferences.edit().commit()
        Handler(Looper.getMainLooper()).postDelayed({
            val nextActivity = if(sSharedPreferences.getString(this.getString(R.string.jwt_key),"").equals("")) SignupActivity::class.java else HomeActivity::class.java
//            val nextActivity = CreateProfileActivity::class.java
            val intent = Intent(this, nextActivity)
            intent.putExtra(this.getString(R.string.sign_in_to_create_profile_phone_number_intent_key),"01056974181")
            startActivity(intent)
            finish()
        }, 1500)
    }
}