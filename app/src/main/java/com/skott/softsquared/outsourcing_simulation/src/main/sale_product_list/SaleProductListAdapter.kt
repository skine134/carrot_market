package com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentFactory
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.SaleItemAdapterBinding
import com.skott.softsquared.outsourcing_simulation.src.main.product_detail.ProductDetailActivity
import com.skott.softsquared.outsourcing_simulation.src.main.product_detail.ProductDetailService
import com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list.model.SaleProductListResponse
import com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list.model.SoldOutRequest
import com.skott.softsquared.outsourcing_simulation.src.main.sell_list.SellListActivity
import com.skott.softsquared.outsourcing_simulation.src.util.adapters.BaseRecyclerMessageViewAdapter
import com.skott.softsquared.outsourcing_simulation.src.util.custom_views.RecyclerMessageView
import com.skott.softsquared.outsourcing_simulation.src.util.lib.convertDpToPixel

class SaleProductListAdapter(
    context: Context
    ,val saleProductListService: SaleProductListService,
    arrayList: ArrayList<SaleProductListResponse>,
    recyclerMessageView: RecyclerMessageView
) : BaseRecyclerMessageViewAdapter<SaleProductListResponse, SaleProductListViewHolder>(
    context,
    arrayList,
    recyclerMessageView
) {
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private var clickSoldOut= -1
    protected lateinit var binding: SaleItemAdapterBinding
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): SaleProductListViewHolder {
        binding = SaleItemAdapterBinding.inflate(inflater,parent,false)
        binding.root.setOnClickListener {
            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra(context.getString(R.string.home_activity_to_product_detail_activity_intent_key),arrayList[position].idx)
            (context as Activity).startActivity(intent)
        }
        binding.changeReservation.setOnClickListener{
        }
        binding.changeSoldOut.setOnClickListener{
            saleProductListService.tryPostSoldOut(SoldOutRequest(arrayList[position].idx))
            clickSoldOut=position
        }
        return SaleProductListViewHolder(context,binding)
    }

    override fun onBindViewHolder(holder: SaleProductListViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }
    fun getSoldItemPosition():Int
    {
        return clickSoldOut
    }
}