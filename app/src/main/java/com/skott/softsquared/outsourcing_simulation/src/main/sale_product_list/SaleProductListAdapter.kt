package com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list

import android.content.Context
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.databinding.SaleItemAdapterBinding
import com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list.model.SaleProductListResponse
import com.skott.softsquared.outsourcing_simulation.src.util.adapters.BaseRecyclerMessageViewAdapter
import com.skott.softsquared.outsourcing_simulation.src.util.custom_views.RecyclerMessageView
import com.skott.softsquared.outsourcing_simulation.src.util.lib.convertDpToPixel

class SaleProductListAdapter(
    context: Context,
    arrayList: ArrayList<SaleProductListResponse>,
    recyclerMessageView: RecyclerMessageView
) : BaseRecyclerMessageViewAdapter<SaleProductListResponse, SaleProductListViewHolder>(
    context,
    arrayList,
    recyclerMessageView
) {
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    protected lateinit var binding: SaleItemAdapterBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaleProductListViewHolder {
        binding = SaleItemAdapterBinding.inflate(inflater,parent,false)
        return SaleProductListViewHolder(context,binding)
    }

    override fun onBindViewHolder(holder: SaleProductListViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }
}