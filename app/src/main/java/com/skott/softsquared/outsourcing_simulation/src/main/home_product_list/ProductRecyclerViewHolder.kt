package com.skott.softsquared.outsourcing_simulation.src.main.home_product_list

import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.databinding.ProductItemAdapterBinding
import com.skott.softsquared.outsourcing_simulation.src.util.custom_views.IconCounterView

class ProductRecyclerViewHolder(val item:ProductItemAdapterBinding) :RecyclerView.ViewHolder(item.root){
    private val name:TextView = item.productNameTextView
    private val town:TextView = item.sellerTownTextView
    private val image: ImageView = item.productImageView
    private val price:TextView = item.productPriceTextView
    private val favorite:IconCounterView = item.favoriteCount
    private val chat:IconCounterView = item.chatCount
    private val multiChat:IconCounterView = item.multiChatCount
    private val time:TextView = item.productUploadTimeTextView
    private val pullUp:TextView = item.pullUpTextView
}