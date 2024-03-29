package com.skott.softsquared.outsourcing_simulation.src.main.buyer_select

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.databinding.BuyerAdapterBinding
import com.skott.softsquared.outsourcing_simulation.src.main.buyer_select.model.BuyerInfo
import com.skott.softsquared.outsourcing_simulation.src.main.buyer_select.model.PostBuyerRequest

class BuyerAdapter(val context: Context, val arrayList: ArrayList<BuyerInfo>) :
    RecyclerView.Adapter<BuyerViewHolder>() {
    private val inflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding: BuyerAdapterBinding
    private var clickPositiopn = -1
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): BuyerViewHolder {
        binding = BuyerAdapterBinding.inflate(inflater, parent, false)
        binding.root.setOnClickListener {
            clickPositiopn = position
            (context as BuyerSelectActivity).service.tryPostBuyerSelect(
                PostBuyerRequest(
                    context.productInfo.idx,
                    arrayList[clickPositiopn].idx
                )
            )
        }
        return BuyerViewHolder(context, binding)
    }

    override fun onBindViewHolder(holder: BuyerViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    override fun getItemCount() = arrayList.size
    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun getClickItem(): Int {
        return clickPositiopn
    }
}