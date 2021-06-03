package com.skott.softsquared.outsourcing_simulation.src.main.sold_product_list

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.SoldItemAdapterBinding
import com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list.model.SaleProductListResponse
import com.skott.softsquared.outsourcing_simulation.src.main.sold_product_list.model.SoldProductListResponse
import com.skott.softsquared.outsourcing_simulation.src.util.lib.getRoundedAllCornerBitmap
import java.io.FileNotFoundException
import java.lang.Exception
import java.text.DecimalFormat

class SoldProductListViewHolder(val context: Context,val binding: SoldItemAdapterBinding): RecyclerView.ViewHolder(binding.root){
    val name: TextView = binding.productNameTextView
    val town: TextView = binding.sellerTownTextView
    val image: ImageView = binding.productImageView
    val price: TextView = binding.productPriceTextView
    val time: TextView = binding.productUploadTimeTextView
    var status :ImageView = binding.status
    fun bind(soldProductListResponse: SoldProductListResponse) {
        name.text = soldProductListResponse.title
        town.text = soldProductListResponse.dong
        val imageString = soldProductListResponse.pictureUrl.toString()
        try{
            getRoundedAllCornerBitmap(context, imageString, 10, image)
        }catch (fe: Exception){
            image.setImageResource(R.drawable.item_default_image)
        }
        price.text = context.getString(R.string.product_price).replace(
            "price",
            DecimalFormat("###,###").format(soldProductListResponse.price.toInt()).toString()
        )
        time.text = soldProductListResponse.passedTime
        status.isGone = soldProductListResponse.status.equals("ONSALE")
    }
}