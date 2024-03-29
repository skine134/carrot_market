package com.skott.softsquared.outsourcing_simulation.src.main.collect_user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.CollectUserManagementAdapterBinding
import com.skott.softsquared.outsourcing_simulation.databinding.CollectUserSaleProductLayoutBinding
import com.skott.softsquared.outsourcing_simulation.src.config.BaseActivity
import com.skott.softsquared.outsourcing_simulation.src.main.collect_user.model.CollectUserResponse
import com.skott.softsquared.outsourcing_simulation.src.main.collect_user_management.CollectUserManagementActivity
import com.skott.softsquared.outsourcing_simulation.src.main.product_detail.SmallProductAdapter
import com.skott.softsquared.outsourcing_simulation.src.util.lib.SpacesItemDecoration
import com.skott.softsquared.outsourcing_simulation.src.util.lib.getScrollListener

class CollectUserActivity:BaseActivity<CollectUserSaleProductLayoutBinding>(CollectUserSaleProductLayoutBinding::inflate),CollectUserView {
    private lateinit var context: Context
    private lateinit var adapter:CollectUserAdapter
    private var page = 1
    private lateinit var collectUserService: CollectUserService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context=this
        collectUserService = CollectUserService(this)
        collectUserService.tryGetCollectUser()
        binding.collectUserSaleProductRecyclerView.addOnScrollListener(getScrollListener
        {
            getCollectUserList()
        })
        setManaegementIntentEvent(binding.managementButton)
    }
    private fun setManaegementIntentEvent(button: Button)
    {
        button.setOnClickListener {
        val intent = Intent(context,CollectUserManagementActivity::class.java)
        startActivity(intent)
    }
    }
    private fun getCollectUserList()
    {
        ++page
//        collectUserService.tryGetCollectUser(
//            villageIdx = 1,
//            rangeLevel = 1,
//            categories = category,
//            lastItemIdx = page
//        )
    }
    override fun onGetCollectUserSuccess(collectUserResponseArray: ArrayList<CollectUserResponse>) {

        binding.collectUserSaleProductRecyclerView.layoutManager = GridLayoutManager(context,2).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return 1
                }
            }
        }
        adapter=CollectUserAdapter(context,collectUserResponseArray)

        binding.collectUserSaleProductRecyclerView.adapter = adapter
        binding.collectUserSaleProductRecyclerView.addItemDecoration(SpacesItemDecoration(2, 20, false))
        adapter.notifyDataSetChanged()
    }

    override fun onGetCollectUserFailure(message: String) {
        Log.e("api error",message)
    }

}