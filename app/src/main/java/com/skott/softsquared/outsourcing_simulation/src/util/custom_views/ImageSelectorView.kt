package com.skott.softsquared.outsourcing_simulation.src.util.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.databinding.ImageSelectorViewBinding

class ImageSelectorView(context: Context, attrs:AttributeSet):ConstraintLayout(context,attrs) {
    private lateinit var binding:ImageSelectorViewBinding
    init{
        init()
    }
    private fun init()
    {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = ImageSelectorViewBinding.inflate(inflater,this,false)
        addView(binding.root)
    }
    fun getRecyclerView():RecyclerView
    {
        return binding.imageRecyclerView
    }
    fun getAddImageButton():LinearLayout
    {
        return binding.galleryImageButtonLayout
    }
}