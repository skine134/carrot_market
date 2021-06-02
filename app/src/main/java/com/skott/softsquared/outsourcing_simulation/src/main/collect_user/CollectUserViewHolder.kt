package com.skott.softsquared.outsourcing_simulation.src.main.collect_user

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.CollectUserAdapterBinding
import com.skott.softsquared.outsourcing_simulation.src.main.collect_user.model.CollectUserResponse
import com.skott.softsquared.outsourcing_simulation.src.util.lib.getRoundedAllCornerBitmap
import java.text.DecimalFormat

class CollectUserViewHolder(val context: Context, binding:CollectUserAdapterBinding) :RecyclerView.ViewHolder(binding.root){
    private val image=binding.image
    private val title=binding.title
    private val name= binding.name
    private val address=binding.address
    private val price=binding.price
    fun bind(collectUserResponse: CollectUserResponse)
    {
        if(collectUserResponse.pictureUrl!=null&&!collectUserResponse.pictureUrl.equals("")&&!collectUserResponse.pictureUrl.equals("null"))
            getRoundedAllCornerBitmap(context,collectUserResponse.pictureUrl,5,image)
        if(!collectUserResponse.pictureUrl.equals("null"))
            price.text=context.getString(R.string.product_detail_price)
                .replace("price", DecimalFormat("###,###").format(collectUserResponse.price))
        title.text=collectUserResponse.title
        name.text=collectUserResponse.nickName
        //TODO producType
        val addressString="${collectUserResponse.siGunGu} ${collectUserResponse.dong}"
        address.text=addressString

    }
}