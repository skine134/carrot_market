package com.skott.softsquared.outsourcing_simulation.src.main.home_chat

import android.content.Context
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.ChatAdapterBinding
import com.skott.softsquared.outsourcing_simulation.src.main.home_chat.model.ChatModel
import com.skott.softsquared.outsourcing_simulation.src.util.lib.getRoundedAllCornerBitmap

class ChatViewHolder(val context: Context, binding:ChatAdapterBinding) :RecyclerView.ViewHolder(binding.root){
    private val image=binding.userImageView
    private val name=binding.name
    private val dong=binding.dong
    private val content=binding.soldProductName
    private val time=binding.time
    fun bind(chatModel: ChatModel)
    {
        content.text = chatModel.content
        dong.text = chatModel.dong
        time.text = chatModel.day
        name.text = chatModel.name
        val tmp = ShapeDrawable(OvalShape())
        tmp.paint.color = context.getColor(R.color.white)
        image.background = tmp
        image.clipToOutline = true
        getRoundedAllCornerBitmap(context,chatModel.image,0,image)
    }
}