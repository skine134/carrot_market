package com.skott.softsquared.outsourcing_simulation.src.util.custom_views

import android.content.Context
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.LightingColorFilter
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.isGone
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.CarrotMarketAppBarLayoutBinding
import com.skott.softsquared.outsourcing_simulation.databinding.SeekTownViewBinding

enum class MapRange {
    KM2,
    KM4,
    KM5
}

class SeekMapView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    private lateinit var binding: SeekTownViewBinding

    init {
        init()
    }

    fun init() {
        val inflate = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = SeekTownViewBinding.inflate(inflate, this, false)
        addView(binding.root)
    }

    fun getMap1km(): ImageView {
        return binding.map1km
    }

    fun getMap2km(): ImageView {
        return binding.map2km
    }

    fun getMap4km(): ImageView {
        return binding.map4km
    }

    fun getMap5km(): ImageView {
        return binding.map5km
    }

    fun setMapFilter(percent: Int) {
        //TODO: stroke가 나타나고 사라지는 형식으로 바꿔야함.
        if (percent < 33) {
            binding.map2km.alpha = getCalCulFilter(percent, MapRange.KM2)
        } else if (percent in 33..66) {
            binding.map4km.alpha = getCalCulFilter(percent, MapRange.KM4)
        } else {
            binding.map5km.alpha = getCalCulFilter(percent, MapRange.KM5)
        }
    }

    fun getCalCulFilter(percent: Int, mapRange: MapRange): Float {
        val result = ((percent - (33 * mapRange.ordinal)) / 33.toFloat())
        Log.d("percent", percent.toString())
        Log.d("ordinal", mapRange.ordinal.toString())
        Log.d("caculResult", result.toString())
        return if (result > 10) 100f else result
    }

}