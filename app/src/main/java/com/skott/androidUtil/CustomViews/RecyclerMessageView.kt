package com.skott.androidUtil.CustomViews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.RecyclerMessageViewFragmentBinding

class RecyclerMessageView(context: Context, attrs: AttributeSet?) :
    ConstraintLayout(context, attrs) {
    var message: String = ""
    lateinit var messageTextView: TextView
    private lateinit var inflater: LayoutInflater
    private lateinit var binding: RecyclerMessageViewFragmentBinding

    init {
        attrs?.run {
            context.obtainStyledAttributes(this, R.styleable.RecycleListView)
        }?.run {
            message = getString(R.styleable.RecyclerMessageView_message)!!
        }
        init()
    }

    private fun init() {
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = RecyclerMessageViewFragmentBinding.inflate(inflater, this, false)
        messageTextView = binding.recyclerTextMessage
        addView(binding.root)
    }

}