package com.skott.softsquared.outsourcing_simulation.src.util.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.RecyclerMessageViewFragmentBinding
import com.skott.softsquared.outsourcing_simulation.src.util.adapters.BaseRecyclerMessageViewAdapter

class RecyclerMessageView(context: Context, attrs: AttributeSet?=null) :
    ConstraintLayout(context, attrs) {
    var message: String = ""
    lateinit var messageTextView: TextView
    private lateinit var inflater: LayoutInflater
    private  lateinit var adapter:BaseRecyclerMessageViewAdapter<*,*>
    private lateinit var binding: RecyclerMessageViewFragmentBinding

    init {
        init()
        attrs.run {
            context.obtainStyledAttributes(this, R.styleable.RecyclerMessageView)
        }.run {
            message = getString(R.styleable.RecyclerMessageView_message)?:""
            messageTextView.text=message
            messageTextView.isGone=true
        }
    }

    private fun init() {
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = RecyclerMessageViewFragmentBinding.inflate(inflater, this, false)
        messageTextView = binding.recyclerTextMessage
        binding.recyclerView.layoutManager =LinearLayoutManager(context)
        addView(binding.root)
    }
    fun getRecyclerView():RecyclerView
    {
        return binding.recyclerView
    }
    fun <T,VH : RecyclerView.ViewHolder>setAdapter(adapter:BaseRecyclerMessageViewAdapter<T,VH>)
    {
        this.adapter = adapter
        binding.recyclerView.adapter = this.adapter
        notifyDataSet()
    }
    fun <T>add(item:T,position:Int=0)
    {
            (adapter.arrayList as ArrayList<T>).add(position, item)
            notifyDataSet()
    }
    fun remove(position: Int)
    {
        adapter.arrayList.removeAt(position)
        notifyDataSet()
    }
    fun notifyDataSet()
    {
        adapter.notifyDataSetChanged()
        binding.recyclerTextMessage.text = message
        binding.recyclerTextMessage.isGone = adapter.arrayList.isNotEmpty()
        binding.recyclerView.isGone = adapter.arrayList.isEmpty()
    }
    fun getTextView():TextView
    {
        return binding.recyclerTextMessage
    }
}