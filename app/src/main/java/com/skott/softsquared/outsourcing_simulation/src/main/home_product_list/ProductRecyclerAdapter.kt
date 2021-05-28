package com.skott.softsquared.outsourcing_simulation.src.main.home_product_list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.skott.softsquared.outsourcing_simulation.databinding.ProductItemAdapterBinding
import com.skott.softsquared.outsourcing_simulation.src.main.home_product_list.model.ProductListResponse
import com.skott.softsquared.outsourcing_simulation.src.util.adapters.BaseRecyclerMessageViewAdapter
import com.skott.softsquared.outsourcing_simulation.src.util.custom_views.RecyclerMessageView

class ProductRecyclerAdapter(context: Context,arrayList: ArrayList<ProductListResponse>,recyclerMessageView: RecyclerMessageView):BaseRecyclerMessageViewAdapter<ProductListResponse,ProductRecyclerViewHolder>(context,arrayList,recyclerMessageView) {
    private val inflater=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding:ProductItemAdapterBinding
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ProductRecyclerViewHolder {
        binding= ProductItemAdapterBinding.inflate(inflater,parent,false)
        return ProductRecyclerViewHolder(context,binding)
    }

    override fun onBindViewHolder(holder: ProductRecyclerViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }
}