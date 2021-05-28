package com.skott.softsquared.outsourcing_simulation.src.main.home_product_list

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.ProductItemAdapterBinding
import com.skott.softsquared.outsourcing_simulation.src.main.home_product_list.model.ProductListResponse
import com.skott.softsquared.outsourcing_simulation.src.util.custom_views.IconCounterView
import com.skott.softsquared.outsourcing_simulation.src.util.lib.getRoundedAllCornerBitmap

class ProductRecyclerViewHolder(val context: Context, val item:ProductItemAdapterBinding) :RecyclerView.ViewHolder(item.root){
    private val name:TextView = item.productNameTextView
    private val town:TextView = item.sellerTownTextView
    private val image: ImageView = item.productImageView
    private val price:TextView = item.productPriceTextView
    private val favorite:IconCounterView = item.favoriteCount
    private val chat:IconCounterView = item.chatCount
    private val multiChat:IconCounterView = item.multiChatCount
    private val time:TextView = item.productUploadTimeTextView
    private val pullUp:TextView = item.pullUpTextView
    var status : String="ONSALE"
    fun bind(productListResponse: ProductListResponse)
    {
        name.text=productListResponse.title
        town.text=productListResponse.dong
        val imageString = productListResponse.pictureUrl.toString()
        if(!imageString.equals("null"))
            getRoundedAllCornerBitmap(context,imageString,20,image)
        price.text = context.getString(R.string.product_price).replace("price",productListResponse.price)
        favorite.setCount(productListResponse.numOfLikes)
        chat.setCount(productListResponse.numOfChats)
        time.text=productListResponse.passedTime
        pullUp.text = if(productListResponse.isOnTop.equals("NO")) "" else "끌올"
        status = productListResponse.status
    }
}