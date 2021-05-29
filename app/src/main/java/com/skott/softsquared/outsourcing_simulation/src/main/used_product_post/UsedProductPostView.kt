package com.skott.softsquared.outsourcing_simulation.src.main.used_product_post

interface UsedProductPostView {
    fun onPostItemUploadSuccess()
    fun onPostItemUploadFailure(message:String)
}