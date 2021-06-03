package com.skott.softsquared.outsourcing_simulation.src.main.home_chat

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import com.skott.softsquared.outsourcing_simulation.databinding.ChatAdapterBinding
import com.skott.softsquared.outsourcing_simulation.src.main.home_chat.model.ChatModel
import com.skott.softsquared.outsourcing_simulation.src.util.adapters.BaseRecyclerMessageViewAdapter
import com.skott.softsquared.outsourcing_simulation.src.util.custom_views.RecyclerMessageView

class ChatAdapter(context: Context, arrayList:ArrayList<ChatModel>,recyclerMessageView: RecyclerMessageView) :BaseRecyclerMessageViewAdapter<ChatModel,ChatViewHolder>(context,arrayList,recyclerMessageView){
    private lateinit var binding: ChatAdapterBinding
    private val inflate = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ChatViewHolder {
        binding= ChatAdapterBinding.inflate(inflate,parent,false)
        return ChatViewHolder(context,binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }


}