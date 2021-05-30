package com.skott.softsquared.outsourcing_simulation.src.main.home_product_list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.FOCUS_BLOCK_DESCENDANTS
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.ProductListFragmentBinding
import com.skott.softsquared.outsourcing_simulation.src.config.BaseFragment
import com.skott.softsquared.outsourcing_simulation.src.main.home_product_list.model.ProductListResponse
import com.skott.softsquared.outsourcing_simulation.src.main.used_product_post.UsedProductCategory
import com.skott.softsquared.outsourcing_simulation.src.main.used_product_post.UsedProductPostActivity
import com.skott.softsquared.outsourcing_simulation.src.util.lib.getScrollListener

class ProductListFragment:BaseFragment<ProductListFragmentBinding>(ProductListFragmentBinding::bind,R.layout.product_list_fragment),ProductListView {
    private lateinit var productRecyclerAdapter: ProductRecyclerAdapter
    private lateinit var productListService: ProductListService
    private var page =0
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

        binding.productList.isNestedScrollingEnabled=false
        productListService.tryGetProductList(villageIdx = 1,rangeLevel = 1,categories = allCategory,lastItemIdx =page)
        productRecyclerAdapter = ProductRecyclerAdapter(requireContext(), arrayList)
        binding.productList.layoutManager=LinearLayoutManager(requireContext())
        binding.productList.adapter = productRecyclerAdapter
        // 네스트스크롤뷰 무한 스크롤
        binding.nestedScrollView.setOnScrollChangeListener() { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            Log.d("scrolling", "")
            val showFirstItem =
                (binding.productList.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
            val lastItem = productRecyclerAdapter.itemCount - 5
            if (showFirstItem >= lastItem) {
                ++page
                productListService.tryGetProductList(
                    villageIdx = 1,
                    rangeLevel = 1,
                    categories = allCategory,
                    lastItemIdx = page
                )
            }
        }


        //리사이클러뷰 무한스크롤
//        binding.productList.addOnScrollListener(getScrollListener{
//            ++page
//            productListService.tryGetProductList(
//                villageIdx = 1,
//                rangeLevel = 1,
//                categories = allCategory,
//                lastItemIdx = page
//            )
//        })
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
        productRecyclerAdapter.notifyDataSetChanged()
    }

    override fun onProductListFailure(message: String) {
        Log.e("api error",message)
    }
}