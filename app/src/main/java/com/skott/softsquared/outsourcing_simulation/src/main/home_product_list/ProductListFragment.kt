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

class ProductListFragment:BaseFragment<ProductListFragmentBinding>(ProductListFragmentBinding::bind,R.layout.product_list_fragment),ProductListView {
    private lateinit var productRecyclerAdapter: ProductRecyclerAdapter
    private lateinit var productListService: ProductListService
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val arrayList = ArrayList<ProductListResponse>()
        productListService= ProductListService(this)
        productListService.tryGetProductList(villageIdx = 1,rangeLevel = 1,categories = "디지털/가전&가구/인테리어",lastItemIdx =0)
        productRecyclerAdapter = ProductRecyclerAdapter(requireContext(),arrayList,binding.productList)
        binding.productList.getRecyclerView().layoutManager=LinearLayoutManager(requireContext())
        binding.productList.getRecyclerView().adapter = productRecyclerAdapter
        return binding.root
    }

    override fun onProductListSuccess(arrayList: ArrayList<ProductListResponse>) {

        productRecyclerAdapter.arrayList.addAll(arrayList)
        showCustomToast("item count : ${productRecyclerAdapter.itemCount}")
        productRecyclerAdapter.notifyChanged()
    }

    override fun onProductListFailure(message: String) {
        showCustomToast(message)
    }
}