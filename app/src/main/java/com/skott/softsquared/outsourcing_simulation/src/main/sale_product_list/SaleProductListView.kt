package com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list

import com.skott.softsquared.outsourcing_simulation.src.main.favorite_product_list.model.FavoriteItemResponse
import com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list.model.SaleProductListResponse
import com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list.model.SoldOutRequest
import com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list.model.SoldResponse

interface SaleProductListView {

    fun onGetSaleProductListViewSuccess(saleProductListResponseArray: ArrayList<SaleProductListResponse>)
    fun onGetSaleProductListViewFailure(message:String)

    fun onPostSoldOutSuccess(soldOutResponseArray: ArrayList<SoldResponse>)
    fun onPostSoldOutFailure(message:String)

}