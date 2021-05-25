package com.skott.androidUtil.custom_views

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.ProfileImageViewBinding

interface ImageResultEvent{
    fun imageSelectListener(uriString:String)
}
class ProfileImageView(context: Context, attrs:AttributeSet) : ConstraintLayout(context,attrs),ImageResultEvent{
    private lateinit var binding:ProfileImageViewBinding
    private lateinit var imageData:String
    init{
        init()
        drawImageRounding()
        setUpdateImageButton()
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
    private fun setUpdateImageButton()
    {
        binding.profileImageUpdateButton.setOnClickListener{
//            Toast.makeText(context,"test",Toast.LENGTH_SHORT).show()
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*")
            (context as Activity).startActivityForResult(intent,0)
        }
    }
    fun getImageView():ImageView
    {
        return binding.profileImage
    }

    override fun imageSelectListener(uriString: String) {
        imageData = uriString
        binding.profileImage.setImageURI(Uri.parse(imageData))
    }
}