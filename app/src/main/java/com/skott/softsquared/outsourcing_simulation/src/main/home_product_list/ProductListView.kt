package com.skott.softsquared.outsourcing_simulation.src.main.home_product_list

import com.skott.softsquared.outsourcing_simulation.src.main.home_product_list.model.ProductListResponse

interface ProductListView {
    fun onProductListSuccess(arrayList:ArrayList<ProductListResponse>)
    fun onProductListFailure(message:String)
}