package com.skott.softsquared.outsourcing_simulation.src.main.sold_product_list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.src.main.buyer_select.BuyerSelectActivity
import com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list.SaleProductListService
import com.skott.softsquared.outsourcing_simulation.src.main.sell_list.BaseSellFragment
import com.skott.softsquared.outsourcing_simulation.src.main.sold_product_list.model.BuyerListResponse
import com.skott.softsquared.outsourcing_simulation.src.main.sold_product_list.model.SoldProductListResponse

class SoldProductListFragment: BaseSellFragment() ,SoldProductListView{
    private lateinit var adapter: SoldProductListAdapter
    override var tabName: String = ""
    override var emptyMessage = ""
    private val soldService = SoldProductListService(this)
    override var service = { soldService.tryGetSoldProductList()}
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tabName = requireContext().getString(R.string.sell_activity_host_sold)
        emptyMessage = requireContext().getString(R.string.sell_activity_list_not_found_sell_list)
            .replace("name", "거래완료")

        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onGetSoldProductListViewSuccess(soldProductListResponseArray: ArrayList<SoldProductListResponse>) {
        adapter=SoldProductListAdapter(requireContext(),soldProductListResponseArray,soldService)
        binding.mySellRecyclerMessageView.setAdapter(adapter)
    }

    override fun onGetSoldProductListViewFailure(message: String) {
        Log.e("api error",message)
    }

    override fun onPatchSaleSuccess() {
        //최신 정보를 불러와서 업데이트
        soldService.tryGetSoldProductList()
    }

    override fun onPatchSaleFailure(message: String) {
        Log.e("api error",message)
    }

    override fun onGetBuyerResponseSuccess(BuyerListArray: ArrayList<BuyerListResponse>) {
        val intent = Intent(requireContext(),BuyerSelectActivity::class.java)
        intent.putExtra(requireContext().getString(R.string.buyer_select_activity_sold_or_sale_intent_key),true)
        intent.putExtra(requireContext().getString(R.string.buyer_select_activity_intent_key),BuyerListArray.toString())
        intent.putExtra(requireContext().getString(R.string.buyer_select_activity_product_intent_key),adapter.arrayList[adapter.getClickItemPosition()].toString())
        startActivity(intent)
    }

    override fun onGetBuyerResponseFailure(message: String) {
        Log.e("api error",message)
    }
}