package com.skott.softsquared.outsourcing_simulation.src.main.home_product_list

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.PleaseTownAuthFragmentBinding
import com.skott.softsquared.outsourcing_simulation.databinding.ProductItemAdapterBinding
import com.skott.softsquared.outsourcing_simulation.databinding.ProductListFragmentBinding
import com.skott.softsquared.outsourcing_simulation.src.config.ITEM_DEFAULT_URL_STRING
import com.skott.softsquared.outsourcing_simulation.src.main.home_product_list.model.ProductListResponse
import com.skott.softsquared.outsourcing_simulation.src.util.custom_views.IconCounterView
import com.skott.softsquared.outsourcing_simulation.src.util.lib.getRoundedAllCornerBitmap
import java.io.FileNotFoundException
import java.text.DecimalFormat

class ProductRecyclerViewHolder(val context: Context, val item: ViewBinding) :
    RecyclerView.ViewHolder(item.root) {
    private lateinit var name: TextView
    private lateinit var town: TextView
    private lateinit var image: ImageView
    private lateinit var price: TextView
    private lateinit var favorite: IconCounterView
    private lateinit var chat: IconCounterView
    private lateinit var multiChat: IconCounterView
    private lateinit var time: TextView
    private lateinit var pullUp: TextView
    private lateinit var noticeView: ConstraintLayout
    private lateinit var status: ImageView

    init {
        if (item is ProductItemAdapterBinding) {
            name = item.productNameTextView
            town = item.sellerTownTextView
            image = item.productImageView
            price = item.productPriceTextView
            favorite = item.favoriteCount
            chat = item.chatCount
            multiChat = item.multiChatCount
            time = item.productUploadTimeTextView
            pullUp = item.pullUpTextView
            status = item.status
        } else {
            noticeView = (item as PleaseTownAuthFragmentBinding).root
        }
    }

    fun bind(productListResponse: ProductListResponse?) {
        if (productListResponse == null)
            return
        name.text = productListResponse.title
        town.text = productListResponse.dong
        val imageString = productListResponse.pictureUrl.toString()
        try{
            getRoundedAllCornerBitmap(context, imageString, 6, image)
        }catch (fe:FileNotFoundException){
            image.setImageResource(R.drawable.item_default_image)

        }
        if(price.text.equals("null"))
            price.text = context.getString(R.string.product_price).replace("price", DecimalFormat("###,###").format(productListResponse.price.toInt()).toString())
        favorite.setCount(productListResponse.numOfLikes)
        chat.setCount(productListResponse.numOfChats)
        time.text = productListResponse.passedTime
        pullUp.text = if (productListResponse.isOnTop.equals("NO")) "" else "끌올"
        status.isGone = true
    }
}