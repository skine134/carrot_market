package com.skott.softsquared.outsourcing_simulation.src.main.home_product_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.ProductListFragmentBinding
import com.skott.softsquared.outsourcing_simulation.src.config.BaseFragment
import com.skott.softsquared.outsourcing_simulation.src.main.home_product_list.model.ProductListResponse

class ProductListFragment:BaseFragment<ProductListFragmentBinding>(ProductListFragmentBinding::bind,R.layout.product_list_fragment) {
    private lateinit var productRecyclerAdapter: ProductRecyclerAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val arrayList = ArrayList<ProductListResponse>()
        setArrayListItem(arrayList)
        productRecyclerAdapter = ProductRecyclerAdapter(context!!,arrayList,binding.productList)
        binding.productList.getRecyclerView().layoutManager=LinearLayoutManager(context)
        binding.productList.getRecyclerView().adapter = productRecyclerAdapter
        productRecyclerAdapter.notifyChanged()
        return binding.root
    }
    private fun setArrayListItem(arrayList: ArrayList<ProductListResponse>)
    {

    }
}