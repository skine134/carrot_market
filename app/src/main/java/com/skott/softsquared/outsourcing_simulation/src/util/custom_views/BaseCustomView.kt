package com.skott.softsquared.outsourcing_simulation.src.util.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.viewbinding.ViewBinding

abstract class BaseCustomView<B: ViewBinding>(private val inflate: (LayoutInflater) -> B, context:Context, attrs:AttributeSet): ViewGroup(context,attrs) {
    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    protected lateinit var binding: B
    init {
        init()
    }
    open fun init()
    {
        binding = inflate(inflater)
        addView(binding.root)
    }
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
    }
}