package com.skott.softsquared.outsourcing_simulation.src.main.product_detail

import android.content.Context
import android.os.Bundle
import com.skott.softsquared.outsourcing_simulation.databinding.ProductDetailLayoutBinding
import com.skott.softsquared.outsourcing_simulation.src.config.BaseActivity

class ProductDetailActivity :BaseActivity<ProductDetailLayoutBinding>(ProductDetailLayoutBinding::inflate){
    private lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context=this
    }
}