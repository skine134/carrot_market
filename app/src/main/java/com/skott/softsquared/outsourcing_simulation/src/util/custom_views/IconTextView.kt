package com.skott.softsquared.outsourcing_simulation.src.util.custom_views

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.IconTextViewBinding

class IconTextView(context: Context, attrs: AttributeSet) :
    ConstraintLayout(context, attrs) {
    private lateinit var binding: IconTextViewBinding

    init {
        init()
        attrs.run {
            context.obtainStyledAttributes(this, R.styleable.IconTextView)
        }.run {
            val text = getString(R.styleable.IconTextView_title)
            binding.iconText.text = text
            binding.iconText.setTextColor(
                getColor(
                    R.styleable.IconTextView_textColor,
                    context.getColor(R.color.black)
                )
            )

            binding.imageIcon.setImageResource(getResourceId(R.styleable.IconTextView_src, 0))
        }
    }

    fun init() {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = IconTextViewBinding.inflate(inflater, this, false)
        addView(binding.root)
    }

    fun getTextView(): TextView {
        return binding.iconText

    }

    fun getImageView(): ImageView {
        return binding.imageIcon
    }
}