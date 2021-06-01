package com.skott.softsquared.outsourcing_simulation.src.main.favorite_product_list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.skott.softsquared.outsourcing_simulation.databinding.FavoriteListAdapterBinding
import com.skott.softsquared.outsourcing_simulation.src.main.favorite_product_list.model.FavoriteItemResponse
import com.skott.softsquared.outsourcing_simulation.src.util.adapters.BaseRecyclerMessageViewAdapter
import com.skott.softsquared.outsourcing_simulation.src.util.custom_views.RecyclerMessageView

class FavoriteProductListAdapter(
    context:Context,
    arrayList: ArrayList<FavoriteItemResponse>,
    recyclerMessageView: RecyclerMessageView
) : BaseRecyclerMessageViewAdapter<FavoriteItemResponse, FavoriteProductListViewHolder>(
    context,
    arrayList,
    recyclerMessageView
) {
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    protected lateinit var fragmentBinding: FavoriteListAdapterBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteProductListViewHolder {
        fragmentBinding = FavoriteListAdapterBinding.inflate(inflater,parent,false)
        return FavoriteProductListViewHolder(context,fragmentBinding)
    }

    override fun onBindViewHolder(holder: FavoriteProductListViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }
}