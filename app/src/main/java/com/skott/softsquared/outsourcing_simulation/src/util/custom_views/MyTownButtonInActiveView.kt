package com.skott.softsquared.outsourcing_simulation.src.util.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.skott.softsquared.outsourcing_simulation.databinding.MyTownButtonActiveViewBinding
import com.skott.softsquared.outsourcing_simulation.databinding.MyTownButtonInactiveViewBinding

class MyTownButtonInActiveView(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {
    private lateinit var binding: MyTownButtonInactiveViewBinding
    init{
        init()
    }
    private fun init()
    {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = MyTownButtonInactiveViewBinding.inflate(inflater,this,false)
        addView(binding.root)
    }
    fun setTown(townName:String)
    {
        binding.myTownName.text = townName
    }
    fun getBinding(): MyTownButtonInactiveViewBinding {
        return binding
    }
}