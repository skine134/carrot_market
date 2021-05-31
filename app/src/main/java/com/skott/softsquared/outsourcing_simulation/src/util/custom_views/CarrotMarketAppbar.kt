package com.skott.softsquared.outsourcing_simulation.src.util.custom_views

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Outline
import android.graphics.Rect
import android.net.Uri
import android.util.AttributeSet
import android.util.Log
import android.view.*
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.isGone
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.CarrotMarketAppBarLayoutBinding
import java.io.File
import java.util.*
import kotlin.collections.HashMap

class CarrotMarketAppbar(context: Context, attrs:AttributeSet?=null) :ConstraintLayout(context,attrs){
    private lateinit var binding:CarrotMarketAppBarLayoutBinding
    private val imageButtonArray =ArrayList<ImageButton>()
    init{
        init()
        attrs?.run {
            context.obtainStyledAttributes(this, R.styleable.CarrotMarketAppbar)
        }?.run {
            val backButtonFlag = getBoolean(R.styleable.CarrotMarketAppbar_back_button,false)
            Log.d("backButton",backButtonFlag.toString())
            binding.backButton.isGone = !backButtonFlag
            val list = getTextArray(R.styleable.CarrotMarketAppbar_android_entries)
            if(list!=null)
                for(imageResource in list)
                {
                    val location = imageResource.toString().split("/")
                    val resourceId = this.resources.getIdentifier(location[2].split(".")[0],location[1],context.packageName)
                    val imageButton = ImageButton(context,null,R.style.AppBarIcon)
                    imageButton.setImageResource(resourceId)

                    binding.mainAppBar.addView(imageButton)
                    imageButton.layoutParams = Toolbar.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                    ).apply {
                        gravity = Gravity.END
                    }
                    imageButton.id = ViewCompat.generateViewId()

                    imageButtonArray.add(imageButton)
                }
            binding.title.text = getString(R.styleable.CarrotMarketAppbar_title)?:""
            binding.bottomLine.isGone = !getBoolean(R.styleable.CarrotMarketAppbar_is_bottom_line,true)
        }
    }
    fun init(){
        val inflate = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = CarrotMarketAppBarLayoutBinding.inflate(inflate,this,false)
        addView(binding.root)
    }
    fun getBackButton():ImageButton
    {
        return binding.backButton
    }
    fun getTitle():TextView
    {
        return binding.title
    }
}