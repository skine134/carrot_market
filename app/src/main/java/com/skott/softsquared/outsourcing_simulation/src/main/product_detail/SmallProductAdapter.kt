package com.skott.softsquared.outsourcing_simulation.src.main.product_detail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.skott.softsquared.outsourcing_simulation.databinding.SmallProductAdapterBinding
import com.skott.softsquared.outsourcing_simulation.src.main.product_detail.model.SmallProduct
import com.skott.softsquared.outsourcing_simulation.src.util.adapters.BaseRecyclerMessageViewAdapter
import com.skott.softsquared.outsourcing_simulation.src.util.custom_views.RecyclerMessageView

class SmallProductAdapter(
    context:Context,
    arrayList: ArrayList<SmallProduct>,
    recyclerMessageView: RecyclerMessageView
) : BaseRecyclerMessageViewAdapter<SmallProduct,SmallProductViewHolder>(context, arrayList, recyclerMessageView) {
    private val inflater=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding:SmallProductAdapterBinding
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): SmallProductViewHolder {
        binding = SmallProductAdapterBinding.inflate(inflater,parent,false)
        return SmallProductViewHolder(context,binding)
    }

    override fun onBindViewHolder(holder: SmallProductViewHolder, position: Int) {
        if(arrayList[position].pictureURL!=null)
            holder.bind(arrayList[position])
    }
}