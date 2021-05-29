package com.skott.softsquared.outsourcing_simulation.src.util.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.databinding.ImageSelectorViewBinding

class ImageSelectorView(context: Context, attrs:AttributeSet):BaseCustomView<ImageSelectorViewBinding>(ImageSelectorViewBinding::inflate,context,attrs) {
    override fun init()
    {
        super.init()
        binding.imageRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
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