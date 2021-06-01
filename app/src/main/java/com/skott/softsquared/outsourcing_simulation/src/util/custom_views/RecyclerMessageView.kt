package com.skott.softsquared.outsourcing_simulation.src.util.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.RecyclerMessageViewFragmentBinding
import com.skott.softsquared.outsourcing_simulation.src.main.home_product_list.ProductRecyclerAdapter
import com.skott.softsquared.outsourcing_simulation.src.util.adapters.BaseRecyclerMessageViewAdapter

class RecyclerMessageView(context: Context, attrs: AttributeSet?=null) :
    ConstraintLayout(context, attrs) {
    var message: String = ""
    lateinit var messageTextView: TextView
    private lateinit var inflater: LayoutInflater
    private  lateinit var adapter:BaseRecyclerMessageViewAdapter<String,RecyclerView.ViewHolder>
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
        binding.recyclerView.layoutManager =LinearLayoutManager(context)
        adapter = object:BaseRecyclerMessageViewAdapter<String,RecyclerView.ViewHolder>(context,ArrayList(),this){
            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): RecyclerView.ViewHolder {
                return object:RecyclerView.ViewHolder(TextView(context)){}
            }

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            }
        }
        binding.recyclerView.adapter = adapter
        adapter.notifyChanged()
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
    fun notifyChange()
    {
        adapter.notifyChanged()
    }
}