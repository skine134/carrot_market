package com.skott.softsquared.outsourcing_simulation.src.main.home_product_list

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.PleaseTownAuthFragmentBinding
import com.skott.softsquared.outsourcing_simulation.databinding.ProductListFragmentBinding
import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.config.BaseFragment
import com.skott.softsquared.outsourcing_simulation.src.main.find_town.model.FindMyTownResponse
import com.skott.softsquared.outsourcing_simulation.src.main.home_product_list.model.ProductListResponse
import com.skott.softsquared.outsourcing_simulation.src.main.certification.models.UserLocation
import com.skott.softsquared.outsourcing_simulation.src.main.used_product_post.UsedProductCategory
import com.skott.softsquared.outsourcing_simulation.src.main.used_product_post.UsedProductPostActivity
import com.skott.softsquared.outsourcing_simulation.src.util.lib.convertDpToPixel
import com.skott.softsquared.outsourcing_simulation.src.util.lib.getScrollListener
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class ProductListFragment:BaseFragment<ProductListFragmentBinding>(ProductListFragmentBinding::bind,R.layout.product_list_fragment),ProductListView {
    private lateinit var productRecyclerAdapter: ProductRecyclerAdapter
    private lateinit var productListService: ProductListService
    private var page =0
    private lateinit var userCategory:String
    private val dong= ArrayList<String>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val arrayList = ArrayList<ProductListResponse>()
        productListService= ProductListService(this)
        val location = ApplicationClass.sSharedPreferences.getString(requireContext().getString(R.string.location_key),"")
        if(!location.equals(""))
        {
            try{
                val data = Json.decodeFromString<ArrayList<UserLocation>>(location!!)
                for(item in data)
                {
                    dong.add(item.dong)
                }
            }catch (e:Exception)
            {
                val data = Json.decodeFromString<FindMyTownResponse>(location!!)
                dong.add(data.village)
            }
            val splitTownName = dong[0].split(" ")
            if (splitTownName.isNotEmpty())
                binding.town.text = splitTownName[splitTownName.size-1]
            else
                binding.town.text = dong[0]
        }
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
                val margin = convertDpToPixel(requireContext(),5).toInt()
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
            getProductList(allCategory)
        })
        getProductList(allCategory)
        setFabEvent(binding.productListFab)
        return binding.root
    }

    private fun getProductList(category:String)
    {
        ++page
        productListService.tryGetProductList(
            villageIdx = 1,
            rangeLevel = 1,
            categories = category,
            lastItemIdx = page
        )
    }
    private fun setFabEvent(fab:FloatingActionButton)
    {
        fab.setOnClickListener{
            val intent= Intent(requireContext(),UsedProductPostActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onGetProductListSuccess(arrayList: ArrayList<ProductListResponse>) {

        productRecyclerAdapter.arrayList.addAll(arrayList)
        Log.d("item_count ",productRecyclerAdapter.itemCount.toString())
        productRecyclerAdapter.notifyDataSetChanged()
    }

    override fun onGetProductListFailure(message: String) {
        Log.e("api error",message)
    }
}