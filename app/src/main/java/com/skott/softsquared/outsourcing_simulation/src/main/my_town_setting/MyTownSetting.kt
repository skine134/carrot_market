package com.skott.softsquared.outsourcing_simulation.src.main.my_town_setting

import android.os.Bundle
import com.skott.softsquared.outsourcing_simulation.databinding.MyTownSettingBinding
import com.skott.softsquared.outsourcing_simulation.src.config.BaseActivity
import com.skott.softsquared.outsourcing_simulation.src.util.custom_views.MyTownButtonView

class MyTownSetting :BaseActivity<MyTownSettingBinding>(MyTownSettingBinding::inflate){
    private var activeTownCount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    fun townDeleteEvent(button:MyTownButtonView)
    {
        if(activeTownCount<=1)
            showWarningDialog(button)
        else
            showCheckDialog(button)

    }
    private fun showWarningDialog(button:MyTownButtonView)
    {
        button.setActive(false)
    }
    private fun showCheckDialog(button:MyTownButtonView)
    {
        button.setActive(false)
    }
}