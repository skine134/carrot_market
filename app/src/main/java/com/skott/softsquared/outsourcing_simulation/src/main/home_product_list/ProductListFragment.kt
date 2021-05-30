package com.skott.softsquared.outsourcing_simulation.src.main.home_product_list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.ProductListFragmentBinding
import com.skott.softsquared.outsourcing_simulation.src.config.BaseFragment
import com.skott.softsquared.outsourcing_simulation.src.main.home_product_list.model.ProductListResponse
import com.skott.softsquared.outsourcing_simulation.src.main.used_product_post.UsedProductCategory
import com.skott.softsquared.outsourcing_simulation.src.main.used_product_post.UsedProductPostActivity

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
        var userCategory = ""
        for(item in UsedProductCategory.values())
        {
            userCategory += item.value+"&"
        }
        val allCategory = userCategory.substring(0,userCategory.lastIndex)
        productListService.tryGetProductList(villageIdx = 1,rangeLevel = 1,categories = allCategory,lastItemIdx =0)
        productRecyclerAdapter = ProductRecyclerAdapter(requireContext(),arrayList,binding.productList)
        binding.productList.getRecyclerView().layoutManager=LinearLayoutManager(requireContext())
        binding.productList.getRecyclerView().adapter = productRecyclerAdapter
        setFabEvent(binding.productListFab)
        return binding.root
    }
    private fun setFabEvent(fab:FloatingActionButton)
    {
        fab.setOnClickListener{
            val intent= Intent(requireContext(),UsedProductPostActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onProductListSuccess(arrayList: ArrayList<ProductListResponse>) {

        productRecyclerAdapter.arrayList.addAll(arrayList)
        showCustomToast("item count : ${productRecyclerAdapter.itemCount}")
        productRecyclerAdapter.notifyChanged()
    }

    override fun onProductListFailure(message: String) {
        Log.e("api error",message)
    }
}