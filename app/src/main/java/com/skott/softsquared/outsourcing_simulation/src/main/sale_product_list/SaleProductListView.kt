package com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list

import com.skott.softsquared.outsourcing_simulation.src.main.favorite_product_list.model.FavoriteItemResponse
import com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list.model.SaleProductListResponse

interface SaleProductListView {
    fun onGetSaleProductListViewSuccess(saleProductListResponseArray: ArrayList<SaleProductListResponse>)
    fun onGetSaleProductListViewFailure(message:String)
}