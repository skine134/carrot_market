package com.skott.softsquared.outsourcing_simulation.src.main.favorite_product_list

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.FavoriteListAdapterBinding
import com.skott.softsquared.outsourcing_simulation.src.main.favorite_product_list.model.FavoriteItemResponse
import com.skott.softsquared.outsourcing_simulation.src.main.product_detail.ProductDetailActivity
import com.skott.softsquared.outsourcing_simulation.src.main.product_detail.ProductDetailService
import com.skott.softsquared.outsourcing_simulation.src.util.adapters.BaseRecyclerMessageViewAdapter
import com.skott.softsquared.outsourcing_simulation.src.util.custom_views.RecyclerMessageView

class FavoriteProductListAdapter(
    context:Context,
    arrayList: ArrayList<FavoriteItemResponse>,
    val service: FavoriteProductListService,
    recyclerMessageView: RecyclerMessageView
) : BaseRecyclerMessageViewAdapter<FavoriteItemResponse, FavoriteProductListViewHolder>(
    context,
    arrayList,
    recyclerMessageView
) {
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    protected lateinit var binding: FavoriteListAdapterBinding
    private var clickPosition = -1
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): FavoriteProductListViewHolder {
        binding = FavoriteListAdapterBinding.inflate(inflater,parent,false)
        binding.root.setOnClickListener {
            clickPosition=position
            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra(context.getString(R.string.home_activity_to_product_detail_activity_intent_key),arrayList[clickPosition].idx)
            (context as Activity).startActivity(intent)
        }
        binding.favoriteCheckBox.setOnClickListener{
            clickPosition=position
            service.tryGetFavoriteProduct(arrayList[clickPosition].idx)
        }
        return FavoriteProductListViewHolder(context,binding)
    }

    override fun onBindViewHolder(holder: FavoriteProductListViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }
    fun getClickPosition():Int
    {
        return clickPosition
    }
}