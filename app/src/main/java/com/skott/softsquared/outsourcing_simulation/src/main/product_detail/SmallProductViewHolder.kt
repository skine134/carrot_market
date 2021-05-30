package com.skott.softsquared.outsourcing_simulation.src.main.product_detail

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.ProductDetailLayoutBinding
import com.skott.softsquared.outsourcing_simulation.databinding.SmallProductAdapterBinding
import com.skott.softsquared.outsourcing_simulation.src.main.product_detail.model.SmallProduct
import com.skott.softsquared.outsourcing_simulation.src.util.lib.getRoundedAllCornerBitmap
import java.text.DecimalFormat

class SmallProductViewHolder(val context: Context, binding: SmallProductAdapterBinding):RecyclerView.ViewHolder(binding.root) {
    val image= binding.image
    val title = binding.title
    val price = binding.price
    var status = ""
    fun bind(smallProduct: SmallProduct)
    {
        getRoundedAllCornerBitmap(context,smallProduct.pictureURL!!,10,image)
        title.text = smallProduct.title

        price.text = context.getString(R.string.product_detail_price)
            .replace("price", DecimalFormat("###,###").format(smallProduct.price.toInt()))
        status = smallProduct.status
    }

}