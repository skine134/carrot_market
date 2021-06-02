package com.skott.softsquared.outsourcing_simulation.src.main.sold_product_list

import com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list.model.SaleProductListResponse
import com.skott.softsquared.outsourcing_simulation.src.main.sold_product_list.model.SoldProductListResponse

interface SoldProductListView {

    fun onGetSoldProductListViewSuccess(soldProductListResponseArray: ArrayList<SoldProductListResponse>)
    fun onGetSoldProductListViewFailure(message:String)

    fun onPatchSaleSuccess()
    fun onPatchSaleFailure(message: String)
}