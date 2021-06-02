package com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.BuyerSelectLayoutBinding
import com.skott.softsquared.outsourcing_simulation.src.main.buyer_select.BuyerSelectActivity
import com.skott.softsquared.outsourcing_simulation.src.main.favorite_product_list.FavoriteProductListAdapter
import com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list.model.SaleProductListResponse
import com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list.model.SoldOutRequest
import com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list.model.SoldResponse
import com.skott.softsquared.outsourcing_simulation.src.main.sell_list.BaseSellFragment
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement

class SaleProductListFragment: BaseSellFragment() ,SaleProductListView{
    private lateinit var adapter: SaleProductListAdapter
    override var tabName: String = ""
    override var emptyMessage = ""
    private val saleProductListService= SaleProductListService(this)
    override var service = {saleProductListService.tryGetSaleProductList()}
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
        adapter = SaleProductListAdapter(requireContext(),saleProductListService,saleProductListResponseArray,binding.mySellRecyclerMessageView)
        binding.mySellRecyclerMessageView.getRecyclerView().adapter=adapter
        adapter.notifyChanged()
    }

    override fun onGetSaleProductListViewFailure(message: String) {
        Log.e("api error",message)
    }

    override fun onPostSoldOutSuccess(soldOutResponseArray: ArrayList<SoldResponse>) {
        val intent = Intent(requireContext(), BuyerSelectActivity::class.java)
        if(!soldOutResponseArray.isEmpty())
        {
            val serializedData = Json.encodeToJsonElement(soldOutResponseArray).toString()
            intent.putExtra(requireContext().getString(R.string.buyer_select_activity_intent_key),serializedData)
        }
        adapter.arrayList.removeAt(adapter.getSoldItemPosition())
        startActivity(intent)
    }

    override fun onPostSoldOutFailure(message: String) {
        Log.e("api error",message)
    }
}