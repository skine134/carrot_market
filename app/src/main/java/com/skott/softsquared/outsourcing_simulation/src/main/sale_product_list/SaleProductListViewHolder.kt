package com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.SaleItemAdapterBinding
import com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list.model.SaleProductListResponse
import com.skott.softsquared.outsourcing_simulation.src.util.lib.getRoundedAllCornerBitmap
import java.text.DecimalFormat

class SaleProductListViewHolder(val context: Context, val binding: SaleItemAdapterBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private var name: TextView = binding.productNameTextView
    private var town: TextView = binding.sellerTownTextView
    private var image: ImageView = binding.productImageView
    private var price: TextView = binding.productPriceTextView
    private var time: TextView = binding.productUploadTimeTextView
    var status: String = "ONSALE"
    fun bind(saleProductListResponse: SaleProductListResponse) {
        name.text = saleProductListResponse.title
        town.text = saleProductListResponse.dong
        val imageString = saleProductListResponse.pictureUrl.toString()
        if (!imageString.equals("null"))
            getRoundedAllCornerBitmap(context, imageString, 20, image)
        price.text = context.getString(R.string.product_price).replace(
            "price",
            DecimalFormat("###,###").format(saleProductListResponse.price.toInt()).toString()
        )
        time.text = saleProductListResponse.passedTime
        status = saleProductListResponse.status
    }
}
