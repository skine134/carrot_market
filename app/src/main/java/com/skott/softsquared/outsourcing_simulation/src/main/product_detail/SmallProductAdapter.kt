package com.skott.softsquared.outsourcing_simulation.src.main.product_detail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.skott.softsquared.outsourcing_simulation.R
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
        binding.root.setOnClickListener{
            val intent = Intent(context,ProductDetailActivity::class.java)
            intent.putExtra(context.getString(R.string.home_activity_to_product_detail_activity_intent_key),arrayList[position].idx)
            (context as Activity).startActivity(intent)
        }
        return SmallProductViewHolder(context,binding)
    }

    override fun onBindViewHolder(holder: SmallProductViewHolder, position: Int) {
        if(arrayList[position].pictureURL!=null) {
            holder.bind(arrayList[position])
        }
    }
}