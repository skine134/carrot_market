package com.skott.softsquared.outsourcing_simulation.src.util.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.RecyclerMessageViewFragmentBinding

class RecyclerMessageView(context: Context, attrs: AttributeSet?=null) :
    ConstraintLayout(context, attrs) {
    var message: String = ""
    lateinit var messageTextView: TextView
    private lateinit var inflater: LayoutInflater
    private lateinit var binding: RecyclerMessageViewFragmentBinding

    init {
        init()
        attrs.run {
            context.obtainStyledAttributes(this, R.styleable.RecyclerMessageView)
        }.run {
            message = getString(R.styleable.RecyclerMessageView_message)?:""

        }
    }

    private fun init() {
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = RecyclerMessageViewFragmentBinding.inflate(inflater, this, false)
        messageTextView = binding.recyclerTextMessage
        addView(binding.root)
    }
    fun getRecyclerView():RecyclerView
    {
        return binding.recyclerView
    }
    fun getTextView():TextView
    {
        return binding.recyclerTextMessage
    }
}