package com.skott.softsquared.outsourcing_simulation.src.main.sold_product_list

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.SoldItemAdapterBinding
import com.skott.softsquared.outsourcing_simulation.src.main.buyer_select.BuyerSelectActivity
import com.skott.softsquared.outsourcing_simulation.src.main.product_detail.ProductDetailActivity
import com.skott.softsquared.outsourcing_simulation.src.main.sold_product_list.model.SoldProductListResponse
import com.skott.softsquared.outsourcing_simulation.src.main.sold_comment.SoldCommentActivity
import com.skott.softsquared.outsourcing_simulation.src.util.adapters.BaseRecyclerMessageViewAdapter
import com.skott.softsquared.outsourcing_simulation.src.util.custom_views.RecyclerMessageView

class SoldProductListAdapter(
    context: Context,
    arrayList: ArrayList<SoldProductListResponse>,
    val service: SoldProductListService
) : BaseRecyclerMessageViewAdapter<SoldProductListResponse, SoldProductListViewHolder>(
    context,
    arrayList
) {
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    protected lateinit var binding: SoldItemAdapterBinding
    private var clickItemPosition = -1
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): SoldProductListViewHolder {
        binding = SoldItemAdapterBinding.inflate(inflater,parent,false)
        binding.root.setOnClickListener {
            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra(context.getString(R.string.home_activity_to_product_detail_activity_intent_key),arrayList[position].idx)
            (context as Activity).startActivity(intent)
        }
        binding.sendSoldComment.setOnClickListener{
            clickItemPosition = position
            service.tryGetBuyerList(arrayList[clickItemPosition].idx)
        }
        binding.moreButton.setOnClickListener{
            val soldBottomSheetDialog=SoldBottomSheetDialog()
            soldBottomSheetDialog.soldEvent = View.OnClickListener{
                service.tryPatchSale(arrayList[position].idx)
                soldBottomSheetDialog.dismiss()
            }
            soldBottomSheetDialog.show((context as Activity as FragmentActivity).supportFragmentManager,soldBottomSheetDialog.tag)
        }
        return SoldProductListViewHolder(context,binding)
    }

    override fun onBindViewHolder(holder: SoldProductListViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }
    fun getClickItemPosition(): Int {
        return clickItemPosition
    }
}