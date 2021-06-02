package com.skott.softsquared.outsourcing_simulation.src.main.buyer_select

import android.content.Context
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.BuyerAdapterBinding
import com.skott.softsquared.outsourcing_simulation.src.main.buyer_select.model.BuyerInfo
import org.w3c.dom.Text

class BuyerViewHolder(val context: Context,binding:BuyerAdapterBinding) :RecyclerView.ViewHolder(binding.root){
    private val name=binding.name
    private val town=binding.dong
    private val time=binding.time
    private val image=binding.userImageView
    private var idx=0
    fun bind(buyerInfo: BuyerInfo)
    {
        name.text=buyerInfo.nickName
        //TODO: 도시정보, 시간
//      town.text=buyerInfo.
//      time.text=buyerInfo.lastChatTime
        idx=buyerInfo.idx
        if(buyerInfo.profilePictureUrl!=null&&!buyerInfo.profilePictureUrl.equals("null")&&!buyerInfo.profilePictureUrl.equals(""))
        {
            val tmp = ShapeDrawable(OvalShape())
            tmp.paint.color = context.getColor(R.color.white)
            image.background = tmp
            image.clipToOutline = true
        }
    }
}