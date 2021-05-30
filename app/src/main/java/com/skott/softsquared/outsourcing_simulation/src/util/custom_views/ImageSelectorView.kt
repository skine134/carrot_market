package com.skott.softsquared.outsourcing_simulation.src.util.custom_views

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.databinding.ImageSelectorViewBinding
import com.skott.softsquared.outsourcing_simulation.src.util.adapters.GalleryPickerAdapter
import com.skott.softsquared.outsourcing_simulation.src.util.lib.showImagePicker

class ImageSelectorView(context: Context, attrs:AttributeSet):ConstraintLayout(context,attrs) {
    private val inflater=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding:ImageSelectorViewBinding
    init{
        init()
    }
    fun init()
    {
        binding = ImageSelectorViewBinding.inflate(inflater,this,false)
        binding.imageRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.galleryImageButtonLayout.setOnClickListener{showImagePicker(context,true
        ) { uriList:Any->
            val arrayList = arrayListOf<String>()
            for(item in uriList as List<Uri>)
                arrayList.add(item.toString())
            if(binding.imageRecyclerView.adapter!=null)
                (binding.imageRecyclerView.adapter as GalleryPickerAdapter).arrayList.addAll(arrayList)
            else
                binding.imageRecyclerView.adapter = GalleryPickerAdapter(context, arrayList)
        }}
        addView(binding.root)
    }
    fun getRecyclerView():RecyclerView
    {
        return binding.imageRecyclerView
    }
    fun getAddImageButton():ConstraintLayout
    {
        return binding.galleryImageButtonLayout
    }
}