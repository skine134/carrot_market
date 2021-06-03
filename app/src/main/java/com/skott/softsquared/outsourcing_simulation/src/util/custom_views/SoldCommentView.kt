package com.skott.softsquared.outsourcing_simulation.src.util.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.MyTownButtonInactiveViewBinding
import com.skott.softsquared.outsourcing_simulation.databinding.SoldCommentOptionBinding

class SoldCommentView(context: Context, attrs:AttributeSet):LinearLayout(context,attrs) {
    private lateinit var binding: SoldCommentOptionBinding
    init{
        init()
        attrs.run {
            context.obtainStyledAttributes(this, R.styleable.SoldCommentView)
        }.run {
            binding.checkbox.isChecked = getBoolean(R.styleable.SoldCommentView_is_checked,false)
            binding.comment.text = getString(R.styleable.SoldCommentView_comment)
        }
    }
    fun init()
    {

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = SoldCommentOptionBinding.inflate(inflater,this,false)
        addView(binding.root)
    }
}