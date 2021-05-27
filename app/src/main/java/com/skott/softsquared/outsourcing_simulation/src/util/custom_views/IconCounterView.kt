package com.skott.softsquared.outsourcing_simulation.src.util.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.IconCountViewBinding

class IconCounterView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    private lateinit var binding: IconCountViewBinding

    init {
        init()

        attrs.run {
            context.obtainStyledAttributes(this, R.styleable.IconCounterView)
        }.run {
            val type = getInteger(R.styleable.IconCounterView_type, -1)
            val count = getInteger(R.styleable.IconCounterView_count, 0)
            if (count == 0)
                binding.root.isGone = true
            else {
                when (type) {
                    (0) -> { //favorite
                        binding.icon.setImageResource(R.drawable.home_post_heart)
                    }
                    (1) -> { //chat
                        binding.icon.setImageResource(R.drawable.home_post_chat)
                    }
                    (2) -> { //multi chat
                        binding.icon.setImageResource(R.drawable.home_post_chat)
                    }
                }
                binding.count.text = count.toString()
            }
        }
    }

    fun init() {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = IconCountViewBinding.inflate(inflater, this, false)
        addView(binding.root)
    }
    fun setCount(count:Int)
    {
        binding.count.text=count.toString()
        binding.root.isGone=count==0
    }
    fun getCount():Int
    {
        return binding.count.text.toString().toInt()
    }
}