package com.skott.softsquared.outsourcing_simulation.src.util.lib

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.ViewPager
import com.skott.softsquared.outsourcing_simulation.databinding.ImageSliderViewBinding
import com.skott.softsquared.outsourcing_simulation.src.util.custom_views.BaseCustomView
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

class ImageSliderView(context: Context, attrs: AttributeSet) :
    ConstraintLayout(context,attrs) {
    private lateinit var binding: ImageSliderViewBinding
    init {
        init()
    }
    open fun init()
    {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = ImageSliderViewBinding.inflate(inflater,this,false)
        addView(binding.root)
    }
    fun getDotIndication(): WormDotsIndicator {
        return binding.dotsIndicator
    }

    fun getViewPager(): ViewPager {
        return binding.imageViewPager
    }
}