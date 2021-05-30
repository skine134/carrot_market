package com.skott.softsquared.outsourcing_simulation.src.main.home_product_list

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.ProductItemAdapterBinding
import com.skott.softsquared.outsourcing_simulation.src.main.home_product_list.model.ProductListResponse
import com.skott.softsquared.outsourcing_simulation.src.main.product_detail.ProductDetailActivity
import com.skott.softsquared.outsourcing_simulation.src.util.adapters.BaseRecyclerMessageViewAdapter
import com.skott.softsquared.outsourcing_simulation.src.util.custom_views.RecyclerMessageView

class ProductRecyclerAdapter(
    val context: Context,
    val arrayList: ArrayList<ProductListResponse>
):
    RecyclerView.Adapter<ProductRecyclerViewHolder>() {
    private val inflater=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding:ProductItemAdapterBinding
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ProductRecyclerViewHolder {
        binding= ProductItemAdapterBinding.inflate(inflater,parent,false)
        binding.root.setOnClickListener{
            val intent= Intent(context,ProductDetailActivity::class.java)
            intent.putExtra(context.getString(R.string.home_activity_to_product_detail_activity_intent_key),arrayList[position].idx)
            context.startActivity(intent)
        }
        return ProductRecyclerViewHolder(context,binding)
    }

    override fun onBindViewHolder(holder: ProductRecyclerViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    override fun getItemCount() =arrayList.size
}