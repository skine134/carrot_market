package com.skott.softsquared.outsourcing_simulation.src.main.product_detail

import com.skott.softsquared.outsourcing_simulation.src.main.product_detail.model.ProductDetailResponse

interface ProductDetailView {
    fun onGetProductDetailSuccess(productDetailResponse: ProductDetailResponse)
    fun onGetProductDetailFailure(message:String)
}