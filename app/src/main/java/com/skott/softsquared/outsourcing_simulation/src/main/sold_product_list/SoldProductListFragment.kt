package com.skott.softsquared.outsourcing_simulation.src.main.sold_product_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list.SaleProductListService
import com.skott.softsquared.outsourcing_simulation.src.main.sell_list.BaseSellFragment
import com.skott.softsquared.outsourcing_simulation.src.main.sold_product_list.model.SoldProductListResponse

class SoldProductListFragment: BaseSellFragment() ,SoldProductListView{
    private lateinit var adapter: SoldProductListAdapter
    override var tabName: String = ""
    override var emptyMessage = ""
    override var service = { SoldProductListService(this).tryGetSoldProductList()}
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
        adapter=SoldProductListAdapter(requireContext(),soldProductListResponseArray,binding.mySellRecyclerMessageView)
        binding.mySellRecyclerMessageView.getRecyclerView().adapter=adapter
    }

    override fun onGetSoldProductListViewFailure(message: String) {
        Log.e("api error",message)
    }

    override fun onPatchSaleSuccess() {
        TODO("Not yet implemented")
    }

    override fun onPatchSaleFailure(message: String) {
        Log.e("api error",message)
    }
}