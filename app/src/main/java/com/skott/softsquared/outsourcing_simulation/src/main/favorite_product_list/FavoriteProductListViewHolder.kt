package com.skott.softsquared.outsourcing_simulation.src.main.favorite_product_list

import android.content.Context
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.FavoriteListAdapterBinding
import com.skott.softsquared.outsourcing_simulation.src.main.favorite_product_list.model.FavoriteItemResponse
import com.skott.softsquared.outsourcing_simulation.src.util.custom_views.IconCounterView
import com.skott.softsquared.outsourcing_simulation.src.util.lib.getRoundedAllCornerBitmap
import java.io.FileNotFoundException
import java.lang.Exception
import java.text.DecimalFormat

class FavoriteProductListViewHolder(val context: Context, binding: FavoriteListAdapterBinding): RecyclerView.ViewHolder(binding.root){
    private var name: TextView = binding.productNameTextView
    private var town: TextView = binding.sellerTownTextView
    private var image: ImageView = binding.productImageView
    private var price: TextView = binding.productPriceTextView
    private var favorite: IconCounterView = binding.favoriteCount
    private var chat: IconCounterView = binding.chatCount
    private var multiChat: IconCounterView = binding.multiChatCount
    private var time: TextView = binding.productUploadTimeTextView
    private var pullUp: TextView = binding.pullUpTextView
    private var favoriteCheckBox:CheckBox = binding.favoriteCheckBox
    var status=binding.status

    fun bind(favoriteItemResponse: FavoriteItemResponse?) {
        if (favoriteItemResponse == null)
            return
        name.text = favoriteItemResponse.title
        town.text = favoriteItemResponse.dong
        val imageString = favoriteItemResponse.pictureUrl.toString()
        try{
            getRoundedAllCornerBitmap(context, imageString, 10, image)
        }catch (fe: Exception){
            image.setImageResource(R.drawable.item_default_image)

        }
        price.text =
            context.getString(R.string.product_price).replace("price", DecimalFormat("###,###").format(favoriteItemResponse.price.toInt()).toString())
        favorite.setCount(favoriteItemResponse.numOfLikes)
        chat.setCount(favoriteItemResponse.numOfChats)
        time.text = favoriteItemResponse.passedTime
        pullUp.text = if (favoriteItemResponse.isOnTop.equals("NO")) "" else "끌올"
        status.isGone = favoriteItemResponse.status.equals("ONSALE")
        favoriteCheckBox.isChecked = favoriteItemResponse.isliked.equals("YES")
    }
}