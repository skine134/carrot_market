package com.skott.softsquared.outsourcing_simulation.src.main.sold_product_list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.skott.softsquared.outsourcing_simulation.databinding.SaleItemAdapterBinding
import com.skott.softsquared.outsourcing_simulation.databinding.SoldItemAdapterBinding
import com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list.SaleProductListViewHolder
import com.skott.softsquared.outsourcing_simulation.src.main.sold_product_list.model.SoldProductListResponse
import com.skott.softsquared.outsourcing_simulation.src.util.adapters.BaseRecyclerMessageViewAdapter
import com.skott.softsquared.outsourcing_simulation.src.util.custom_views.RecyclerMessageView

class SoldProductListAdapter(
    context: Context,
    arrayList: ArrayList<SoldProductListResponse>,
    recyclerMessageView: RecyclerMessageView
) : BaseRecyclerMessageViewAdapter<SoldProductListResponse, SoldProductListViewHolder>(
    context,
    arrayList,
    recyclerMessageView
) {
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    protected lateinit var binding: SoldItemAdapterBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoldProductListViewHolder {
        binding = SoldItemAdapterBinding.inflate(inflater,parent,false)
        return SoldProductListViewHolder(context,binding)
    }

    override fun onBindViewHolder(holder: SoldProductListViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }
}