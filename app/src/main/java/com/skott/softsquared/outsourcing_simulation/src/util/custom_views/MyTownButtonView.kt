package com.skott.softsquared.outsourcing_simulation.src.util.custom_views

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import com.skott.softsquared.outsourcing_simulation.databinding.MyTownButtonViewBinding
import com.skott.softsquared.outsourcing_simulation.databinding.MyTownSettingBinding
import com.skott.softsquared.outsourcing_simulation.src.main.find_town.FindTownActivity
import com.skott.softsquared.outsourcing_simulation.src.main.my_town_setting.MyTownSetting

private const val RESULT_CODE = 200
class MyTownButtonView(context:Context,attrs:AttributeSet,val myTownSetting: MyTownSetting):ConstraintLayout(context,attrs) {
    private lateinit var binding: MyTownButtonViewBinding
    private var activeCount=0
    init{
        init()
    }
    fun init()
    {
        val inflater= context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = MyTownButtonViewBinding.inflate(inflater,this,false)
        setDeleteEvent(binding.myTownActiveView)
        setIntentFindTownEvent(binding.myTownInactiveView)
        addView(binding.root)
    }
    fun setActive(isActive:Boolean)
    {
        binding.myTownInactiveView.isGone=isActive
        binding.myTownActiveView.isGone=!isActive
    }
    private fun setDeleteEvent(myTownButtonActiveView: MyTownButtonActiveView)
    {
        myTownButtonActiveView.getBinding().myTownDeleteButton.setOnClickListener{
            myTownSetting.townDeleteEvent(this)
        }
    }
    private fun setIntentFindTownEvent(inActiveButton:ImageButton)
    {
        inActiveButton.setOnClickListener{
            val intent = Intent(context,FindTownActivity::class.java)
            (context as Activity).startActivityForResult(intent,RESULT_CODE)
        }
    }
}