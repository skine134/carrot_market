package com.skott.softsquared.outsourcing_simulation.src.util.custom_views

import android.content.Context
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.LightingColorFilter
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
import com.skott.softsquared.outsourcing_simulation.databinding.SeekMapViewBinding

enum class MapRange {
    KM10,
    KM15,
    KM20
}

class SeekMapView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    private lateinit var binding: SeekMapViewBinding

    init {
        init()
    }

    fun init() {
        val inflate = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = SeekMapViewBinding.inflate(inflate, this, false)
        addView(binding.root)
    }

    fun getMap5km(): ImageView {
        return binding.map5km
    }

    fun getMap10km(): ImageView {
        return binding.map10km
    }

    fun getMap15km(): ImageView {
        return binding.map15km
    }

    fun getMap20km(): ImageView {
        return binding.map20km
    }

    fun setMapFilter(percent: Int) {
        //TODO: stroke가 나타나고 사라지는 형식으로 바꿔야함.
        if (percent < 33) {
            binding.map10km.colorFilter =
                LightingColorFilter(getCalCulFilter(percent, MapRange.KM10), 0)
        } else if (33 <= percent && 67 < percent) {
            binding.map15km.colorFilter =
                LightingColorFilter(getCalCulFilter(percent, MapRange.KM15), 0)
        } else {
            binding.map20km.colorFilter =
                LightingColorFilter(getCalCulFilter(percent, MapRange.KM20), 0)
        }
    }

    fun getCalCulFilter(percent: Int, mapRange: MapRange): Int {
        return (percent + 1 - (33 * mapRange.ordinal)) / 100 + 128
    }
}