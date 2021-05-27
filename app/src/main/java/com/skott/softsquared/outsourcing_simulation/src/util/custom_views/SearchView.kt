package com.skott.softsquared.outsourcing_simulation.src.util.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.skott.softsquared.outsourcing_simulation.databinding.SearchViewBinding

class SearchView(context: Context, attrs:AttributeSet) :ConstraintLayout(context,attrs){
    private lateinit var binding:SearchViewBinding
    init{
        init()
    }
    fun init()
    {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = SearchViewBinding.inflate(inflater,this,false)
        addView(binding.root)
    }
}