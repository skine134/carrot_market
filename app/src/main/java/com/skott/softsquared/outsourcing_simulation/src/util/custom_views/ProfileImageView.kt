package com.skott.softsquared.outsourcing_simulation.src.util.custom_views

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.net.Uri
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.ProfileImageViewBinding
import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.util.lib.uploadImageToFireBase
import java.io.ByteArrayOutputStream
import java.util.*

interface ImageResultEvent{
    fun imageSelectListener(uriString:String)
}
class ProfileImageView(context: Context, attrs:AttributeSet) : ConstraintLayout(context,attrs),ImageResultEvent{
    private lateinit var binding:ProfileImageViewBinding
    private lateinit var imageData:String
    private lateinit var imageUrl:String
    init{
        init()
        drawImageRounding()
    }
    private fun init()
    {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = ProfileImageViewBinding.inflate(inflater,this,false)
        addView(binding.root)
    }
    private fun drawImageRounding()
    {
        val tmp = ShapeDrawable(OvalShape())
        tmp.paint.color = context.getColor(R.color.white)
        binding.profileImage.background = tmp
        binding.profileImage.clipToOutline = true
        binding.profileImageUpdateButton.background = tmp
        binding.profileImageUpdateButton.clipToOutline = true
    }

    fun getImageView():ImageView
    {
        return binding.profileImage
    }
    fun getCameraButton():ImageButton
    {
        return binding.profileImageUpdateButton
    }
    fun getImageUrl():String
    {
        return imageUrl
    }
    override fun imageSelectListener(uriString: String) {
        if(uriString.equals("")||uriString.equals("null"))
            return
        imageData = uriString
        binding.profileImage.setImageURI(Uri.parse(imageData))
        imageUrl = uploadImageToFireBase(binding.profileImage)

    }

}