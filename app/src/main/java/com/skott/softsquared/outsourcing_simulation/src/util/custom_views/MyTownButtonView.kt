package com.skott.softsquared.outsourcing_simulation.src.util.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import com.skott.softsquared.outsourcing_simulation.databinding.MyTownButtonViewBinding
import com.skott.softsquared.outsourcing_simulation.databinding.MyTownSettingBinding

class MyTownButtonView(context:Context,attrs:AttributeSet):ConstraintLayout(context,attrs) {
    private lateinit var binding: MyTownButtonViewBinding
    private var activeCount=0
    init{
        init()
    }
    fun init()
    {
        val inflater= context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = MyTownButtonViewBinding.inflate(inflater,this,false)
        addView(binding.root)
    }
    fun setActive(isActive:Boolean)
    {
        binding.myTownInactiveView.isGone=isActive
        binding.myTownActiveView.isGone=!isActive
    }
}