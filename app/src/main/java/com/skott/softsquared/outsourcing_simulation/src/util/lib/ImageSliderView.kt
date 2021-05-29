package com.skott.softsquared.outsourcing_simulation.src.util.lib

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager.widget.ViewPager
import com.skott.softsquared.outsourcing_simulation.databinding.ImageSliderViewBinding
import com.skott.softsquared.outsourcing_simulation.src.util.custom_views.BaseCustomView
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class ImageSliderView(context: Context, attrs: AttributeSet) :
    BaseCustomView<ImageSliderViewBinding>(ImageSliderViewBinding::inflate, context, attrs) {

    fun getDotIndication(): DotsIndicator {
        return binding.dotsIndicator
    }

    fun getViewPager(): ViewPager {
        return binding.imageViewPager
    }
}