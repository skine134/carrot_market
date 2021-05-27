package com.skott.softsquared.outsourcing_simulation.src.util.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.LinearLayout
import com.skott.softsquared.outsourcing_simulation.databinding.MyTownButtonActiveViewBinding
import com.skott.softsquared.outsourcing_simulation.databinding.MyTownButtonViewBinding
import com.skott.softsquared.outsourcing_simulation.src.main.my_town_setting.MyTownSetting

class MyTownButtonActiveView(context: Context, attrs:AttributeSet):LinearLayout(context, attrs) {
    private lateinit var binding: MyTownButtonActiveViewBinding
    init{
        init()
    }
    private fun init()
    {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = MyTownButtonActiveViewBinding.inflate(inflater,this,false)
        addView(binding.root)
    }
    fun setTown(townName:String)
    {
        binding.myTownName.text = townName
    }
    fun getBinding(): MyTownButtonActiveViewBinding {
        return binding
    }
}