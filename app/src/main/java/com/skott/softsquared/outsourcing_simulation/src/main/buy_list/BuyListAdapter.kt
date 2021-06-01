package com.skott.softsquared.outsourcing_simulation.src.main.buy_list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.skott.softsquared.outsourcing_simulation.databinding.BuyListItemAdapterBinding
import com.skott.softsquared.outsourcing_simulation.databinding.PleaseTownAuthFragmentBinding
import com.skott.softsquared.outsourcing_simulation.databinding.ProductItemAdapterBinding
import com.skott.softsquared.outsourcing_simulation.src.main.buy_list.model.BuyListResponse
import com.skott.softsquared.outsourcing_simulation.src.main.home_product_list.model.ProductListResponse
import com.skott.softsquared.outsourcing_simulation.src.util.adapters.BaseRecyclerMessageViewAdapter
import com.skott.softsquared.outsourcing_simulation.src.util.custom_views.RecyclerMessageView

class BuyListAdapter(
    context: Context,
    arrayList: ArrayList<BuyListResponse>,
    recyclerMessageView: RecyclerMessageView
) : BaseRecyclerMessageViewAdapter<BuyListResponse, BuyListViewHolder>(
    context,
    arrayList,
    recyclerMessageView
) {
    private val inflater=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding: BuyListItemAdapterBinding
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): BuyListViewHolder {
        binding = BuyListItemAdapterBinding.inflate(inflater,parent,false)
        return BuyListViewHolder(context,binding)
    }

    override fun onBindViewHolder(holder: BuyListViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }
}