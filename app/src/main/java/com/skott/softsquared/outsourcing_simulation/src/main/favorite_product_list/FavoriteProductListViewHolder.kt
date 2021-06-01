package com.skott.softsquared.outsourcing_simulation.src.main.favorite_product_list

import android.content.Context
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.FavoriteListAdapterBinding
import com.skott.softsquared.outsourcing_simulation.src.main.favorite_product_list.model.FavoriteItemResponse
import com.skott.softsquared.outsourcing_simulation.src.util.custom_views.IconCounterView
import com.skott.softsquared.outsourcing_simulation.src.util.lib.getRoundedAllCornerBitmap
import java.text.DecimalFormat

class FavoriteProductListViewHolder(val context: Context, binding: FavoriteListAdapterBinding): RecyclerView.ViewHolder(binding.root){
    private var name: TextView = binding.favoriteListAdapter.productNameTextView
    private var town: TextView = binding.favoriteListAdapter.sellerTownTextView
    private var image: ImageView = binding.favoriteListAdapter.productImageView
    private var price: TextView = binding.favoriteListAdapter.productPriceTextView
    private var favorite: IconCounterView = binding.favoriteListAdapter.favoriteCount
    private var chat: IconCounterView = binding.favoriteListAdapter.chatCount
    private var multiChat: IconCounterView = binding.favoriteListAdapter.multiChatCount
    private var time: TextView = binding.favoriteListAdapter.productUploadTimeTextView
    private var pullUp: TextView = binding.favoriteListAdapter.pullUpTextView
    private var favoriteCheckBox:CheckBox = binding.favoriteCheckBox
    var status: String = "ONSALE"

    fun bind(favoriteItemResponse: FavoriteItemResponse?) {
        if (favoriteItemResponse == null)
            return
        name.text = favoriteItemResponse.title
        town.text = favoriteItemResponse.dong
        val imageString = favoriteItemResponse.pictureUrl.toString()
        if (!imageString.equals("null"))
            getRoundedAllCornerBitmap(context, imageString, 20, image)
        price.text =
            context.getString(R.string.product_price).replace("price", DecimalFormat("###,###").format(favoriteItemResponse.price.toInt()).toString())
        favorite.setCount(favoriteItemResponse.numOfLikes)
        chat.setCount(favoriteItemResponse.numOfChats)
        time.text = favoriteItemResponse.passedTime
        pullUp.text = if (favoriteItemResponse.isOnTop.equals("NO")) "" else "끌올"
        status = favoriteItemResponse.status
        favoriteCheckBox.isChecked = favoriteItemResponse.isliked.equals("YES")
    }
}