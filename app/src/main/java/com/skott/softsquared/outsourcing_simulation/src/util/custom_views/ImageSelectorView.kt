package com.skott.softsquared.outsourcing_simulation.src.util.custom_views

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.ImageSelectorViewBinding
import com.skott.softsquared.outsourcing_simulation.src.main.gallery_picker.model.Picture
import com.skott.softsquared.outsourcing_simulation.src.util.adapters.GalleryPickerAdapter
import com.skott.softsquared.outsourcing_simulation.src.util.lib.showImagePicker
import com.skott.softsquared.outsourcing_simulation.src.util.lib.uploadImageToFireBase
import kotlin.concurrent.thread

interface PictureListener {
    fun notifyPictureCounter()
    fun getAllPicture(event: (arrayList: ArrayList<Picture>) -> Unit)
}

class ImageSelectorView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs),
    PictureListener {
    private val inflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding: ImageSelectorViewBinding
    private lateinit var adapter: GalleryPickerAdapter
    init {
        init()
    }

    fun init() {
        binding = ImageSelectorViewBinding.inflate(inflater, this, false)
        Log.d("iconsize", binding.imageIcon.layoutParams.width.toString())
        Log.d("displaySize", context.resources.displayMetrics.widthPixels.toString())
        binding.imageRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.galleryImageButtonLayout.setOnClickListener {
            if (binding.imageRecyclerView.adapter != null && adapter.itemCount >= 10) {
                Toast.makeText(context, "10개 이상 추가 불가능합니다", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val maxNum =
                10 - if (binding.imageRecyclerView.adapter == null) 0 else binding.imageRecyclerView.adapter!!.itemCount
            Log.d("MaxNum", maxNum.toString())
            showImagePicker(context, true, maxNum = maxNum) { uriList: Any ->
                val arrayList = arrayListOf<String>()
                for (item in uriList as List<Uri>)
                    arrayList.add(item.toString())
                if (binding.imageRecyclerView.adapter != null) {
                    adapter.arrayList.addAll(arrayList)
                    adapter.notifyDataSetChanged()
                    Log.d("selected_image_count",adapter.arrayList.size.toString())
                }
                else {
                    adapter = GalleryPickerAdapter(context, arrayList, this)
                    binding.imageRecyclerView.adapter = adapter
                }
                binding.iconText.text = "${adapter.itemCount}/10"
            }
        }
        addView(binding.root)
    }

    fun getRecyclerView(): RecyclerView {
        return binding.imageRecyclerView
    }

    fun getAddImageButton(): ConstraintLayout {
        return binding.galleryImageButtonLayout
    }

    override fun notifyPictureCounter() {
        binding.iconText.text = "${adapter.itemCount}/10"
    }

    override fun getAllPicture(event: (arrayList: ArrayList<Picture>) -> Unit) {

        if (binding.imageRecyclerView.adapter == null || adapter.itemCount == 0)
            return
        val size = adapter.arrayList.size
        val arrayList = ArrayList<Picture>(size)
        var count = 0
        for (i in 0 until size) {
            val item =
                getRecyclerView().layoutManager!!.findViewByPosition(i)!!.findViewById<ImageView>(
                    R.id.selected_image
                )
            uploadImageToFireBase(item) {
                arrayList.add(i, Picture(it.pictureId,"https://firebasestorage.googleapis.com${it.pictureUrl.replace("image/","image%2F")}?alt=media"))
                count++
            }
        }
        thread {
            while (count<size)
            {
                Thread.sleep(5)
            }
            event(arrayList)
        }
    }
}