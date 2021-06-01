package com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.src.main.favorite_product_list.FavoriteProductListAdapter
import com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list.model.SaleProductListResponse
import com.skott.softsquared.outsourcing_simulation.src.main.sell_list.BaseSellFragment

class SaleProductListFragment: BaseSellFragment() ,SaleProductListView{
    private lateinit var adapter: SaleProductListAdapter
    override var tabName: String = ""
    override var emptyMessage = ""
    override var service = {SaleProductListService(this).tryGetSaleProductList()}
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tabName = requireContext().getString(R.string.sell_activity_host_sale)
        emptyMessage = requireContext().getString(R.string.sell_activity_list_not_found_sell_list)
            .replace("name", "판매중인")
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onGetSaleProductListViewSuccess(saleProductListResponseArray: ArrayList<SaleProductListResponse>) {
        adapter = SaleProductListAdapter(requireContext(),saleProductListResponseArray,binding.mySellRecyclerMessageView)
        binding.mySellRecyclerMessageView.getRecyclerView().adapter=adapter
    }

    override fun onGetSaleProductListViewFailure(message: String) {
        Log.e("api error",message)
    }
}