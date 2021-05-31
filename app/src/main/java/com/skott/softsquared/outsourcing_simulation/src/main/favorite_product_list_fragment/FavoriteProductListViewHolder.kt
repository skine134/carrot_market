package com.skott.softsquared.outsourcing_simulation.src.main.favorite_product_list_fragment

import android.content.Context
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.FavoriteListAdapterBinding
import com.skott.softsquared.outsourcing_simulation.src.main.favorite_product_list_fragment.model.FavoriteItemModel
import com.skott.softsquared.outsourcing_simulation.src.util.custom_views.IconCounterView
import com.skott.softsquared.outsourcing_simulation.src.util.lib.getRoundedAllCornerBitmap
import java.text.DecimalFormat

class FavoriteProductListViewHolder(val context: Context, binding: FavoriteListAdapterBinding): RecyclerView.ViewHolder(binding.root){
    private var name: TextView
    private var town: TextView
    private var image: ImageView
    private var price: TextView
    private var favorite: IconCounterView
    private var chat: IconCounterView
    private var multiChat: IconCounterView
    private var time: TextView
    private var pullUp: TextView
    private var favoriteCheckBox:CheckBox
    var status: String = "ONSALE"
    init {
            name = binding.favoriteListAdapter.productNameTextView
            town = binding.favoriteListAdapter.sellerTownTextView
            image = binding.favoriteListAdapter.productImageView
            price = binding.favoriteListAdapter.productPriceTextView
            favorite = binding.favoriteListAdapter.favoriteCount
            chat = binding.favoriteListAdapter.chatCount
            multiChat = binding.favoriteListAdapter.multiChatCount
            time = binding.favoriteListAdapter.productUploadTimeTextView
            pullUp = binding.favoriteListAdapter.pullUpTextView
        favoriteCheckBox= binding.favoriteCheckBox
    }

    fun bind(favoriteItemModel: FavoriteItemModel?) {
        if (favoriteItemModel == null)
            return
        name.text = favoriteItemModel.title
        town.text = favoriteItemModel.dong
        val imageString = favoriteItemModel.pictureUrl.toString()
        if (!imageString.equals("null"))
            getRoundedAllCornerBitmap(context, imageString, 20, image)
        price.text =
            context.getString(R.string.product_price).replace("price", DecimalFormat("###,###").format(favoriteItemModel.price.toInt()).toString())
        favorite.setCount(favoriteItemModel.numOfLikes)
        chat.setCount(favoriteItemModel.numOfChats)
        time.text = favoriteItemModel.passedTime
        pullUp.text = if (favoriteItemModel.isOnTop.equals("NO")) "" else "끌올"
        status = favoriteItemModel.status
        favoriteCheckBox.isChecked = favoriteItemModel.isliked.equals("YES")
    }
}