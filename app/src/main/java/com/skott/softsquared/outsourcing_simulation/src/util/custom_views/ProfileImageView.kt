package com.skott.softsquared.outsourcing_simulation.src.util.custom_views

import android.content.Context
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.ProfileImageViewBinding
import com.skott.softsquared.outsourcing_simulation.src.main.gallery_picker.model.Picture
import com.skott.softsquared.outsourcing_simulation.src.util.lib.uploadImageViewToFireBase

interface ImageResultEvent{
    fun imageSelectListener(uriString:String)
}
class ProfileImageView(context: Context, attrs:AttributeSet) : ConstraintLayout(context,attrs),ImageResultEvent{
    private lateinit var binding:ProfileImageViewBinding
    private lateinit var imageData:String
    private lateinit var picture: Picture
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
    fun getPicture():Picture
    {
        return picture
    }
    override fun imageSelectListener(uriString: String) {
        if(uriString.equals("")||uriString.equals("null"))
            return
        imageData = uriString
        binding.profileImage.setImageURI(Uri.parse(imageData))
        uploadImageViewToFireBase(binding.profileImage){picture=it}
    }

}