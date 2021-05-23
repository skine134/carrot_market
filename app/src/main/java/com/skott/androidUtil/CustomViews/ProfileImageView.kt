package com.skott.androidUtil.CustomViews

import android.content.Context
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.ProfileImageViewBinding

class ProfileImageView(context: Context, attrs:AttributeSet) : ConstraintLayout(context,attrs) {
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
//        binding.profileImageUpdateButton.layoutParams.width = binding.profileImage.width/10
        binding.profileImageUpdateButton.background = tmp
        binding.profileImageUpdateButton.clipToOutline = true
    }
    private fun setUpdateImageButton()
    {
        binding.profileImageUpdateButton.setOnClickListener{
            Toast.makeText(context,"test",Toast.LENGTH_SHORT).show()
        }
    }


}