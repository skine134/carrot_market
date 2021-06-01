package com.skott.softsquared.outsourcing_simulation.src.main.buy_list

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.BuyListItemAdapterBinding
import com.skott.softsquared.outsourcing_simulation.databinding.SoldItemAdapterBinding
import com.skott.softsquared.outsourcing_simulation.src.main.buy_list.model.BuyListResponse
import com.skott.softsquared.outsourcing_simulation.src.main.sold_product_list.model.SoldProductListResponse
import com.skott.softsquared.outsourcing_simulation.src.util.lib.getRoundedAllCornerBitmap
import java.io.FileNotFoundException
import java.lang.Exception
import java.text.DecimalFormat

//TODO check
class BuyListViewHolder(val context: Context, val binding: BuyListItemAdapterBinding): RecyclerView.ViewHolder(binding.root){
    val name: TextView = binding.productNameTextView
    val town: TextView = binding.sellerTownTextView
    val image: ImageView = binding.productImageView
    val price: TextView = binding.productPriceTextView
    val time: TextView = binding.productUploadTimeTextView
    var status = "ONSALE"
    fun bind(buyListResponse: BuyListResponse) {
        name.text = buyListResponse.title
        town.text = buyListResponse.dong
        val imageString = buyListResponse.pictureUrl.toString()
        try{
            getRoundedAllCornerBitmap(context, imageString, 10, image)
        }catch (fe: Exception){
            image.setImageResource(R.drawable.item_default_image)

        }
        price.text = context.getString(R.string.product_price).replace(
            "price",
            DecimalFormat("###,###").format(buyListResponse.price.toInt()).toString()
        )
        time.text = buyListResponse.passedTime
        status = buyListResponse.status
    }
}