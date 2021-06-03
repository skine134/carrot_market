package com.skott.softsquared.outsourcing_simulation.src.main.start_auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.StartTownAuthLayoutBinding
import com.skott.softsquared.outsourcing_simulation.src.config.BaseActivity
import com.skott.softsquared.outsourcing_simulation.src.main.find_town.FindTownActivity

class StartAuthActivity :
    BaseActivity<StartTownAuthLayoutBinding>(StartTownAuthLayoutBinding::inflate) {
    private val context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFindTownIntentEvent(binding.startAuthButton)
    }

    private fun setFindTownIntentEvent(button: Button) {
        button.setOnClickListener {
            val intent = Intent(this, FindTownActivity::class.java)
            intent.putExtra(context.getString(R.string.my_carrot_find_my_address_intent_key),-200)
            startActivity(intent)
            finish()
        }
    }

    private fun showNotBackToast() {
        showCustomToast(context.getString(R.string.certificate_not_back))
    }

    override fun onBackPressed() {
        showNotBackToast()
    }
}