package com.skott.softsquared.outsourcing_simulation.src.main.home_product_list

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.FOCUS_BLOCK_DESCENDANTS
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.PleaseTownAuthFragmentBinding
import com.skott.softsquared.outsourcing_simulation.databinding.ProductListFragmentBinding
import com.skott.softsquared.outsourcing_simulation.src.config.BaseFragment
import com.skott.softsquared.outsourcing_simulation.src.main.home_product_list.model.ProductListResponse
import com.skott.softsquared.outsourcing_simulation.src.main.used_product_post.UsedProductCategory
import com.skott.softsquared.outsourcing_simulation.src.main.used_product_post.UsedProductPostActivity
import com.skott.softsquared.outsourcing_simulation.src.util.lib.convertDpToPixel
import com.skott.softsquared.outsourcing_simulation.src.util.lib.getScrollListener

class ProductListFragment:BaseFragment<ProductListFragmentBinding>(ProductListFragmentBinding::bind,R.layout.product_list_fragment),ProductListView {
    private lateinit var productRecyclerAdapter: ProductRecyclerAdapter
    private lateinit var productListService: ProductListService
    private var page =0
    private lateinit var userCategory:String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val arrayList = ArrayList<ProductListResponse>()
        productListService= ProductListService(this)
        userCategory = ""
        for(item in UsedProductCategory.values())
        {
            userCategory += item.value+"&"
        }
        val dividerItemDecoration = object:DividerItemDecoration(binding.productList.context,LinearLayoutManager(context).orientation){
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                val margin = convertDpToPixel(requireContext(),20).toInt()
                outRect.top = margin
                outRect.bottom = margin
            }
        }
        binding.productList.addItemDecoration(dividerItemDecoration)

        val allCategory = userCategory.substring(0,userCategory.lastIndex)
        binding.productList.isNestedScrollingEnabled=false
        val fragment=PleaseTownAuthFragmentBinding.inflate(requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater,container,false)
        productRecyclerAdapter = ProductRecyclerAdapter(requireContext(), arrayList,fragment)
        binding.productList.layoutManager=LinearLayoutManager(requireContext())
        binding.productList.adapter = productRecyclerAdapter

        //리사이클러뷰 무한스크롤
        binding.productList.addOnScrollListener(getScrollListener{
            ++page
            productListService.tryGetProductList(
                villageIdx = 1,
                rangeLevel = 1,
                categories = allCategory,
                lastItemIdx = page
            )
        })
        setFabEvent(binding.productListFab)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val allCategory = userCategory.substring(0,userCategory.lastIndex)
        productListService.tryGetProductList(villageIdx = 1,rangeLevel = 1,categories = allCategory,lastItemIdx =page)
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
        Log.d("item count ",productRecyclerAdapter.itemCount.toString())
        productRecyclerAdapter.notifyDataSetChanged()
    }

    override fun onProductListFailure(message: String) {
        Log.e("api error",message)
    }
}