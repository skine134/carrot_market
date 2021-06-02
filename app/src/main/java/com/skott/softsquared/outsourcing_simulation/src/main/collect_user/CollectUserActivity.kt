package com.skott.softsquared.outsourcing_simulation.src.main.collect_user

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.skott.softsquared.outsourcing_simulation.databinding.CollectUserSaleProductLayoutBinding
import com.skott.softsquared.outsourcing_simulation.src.config.BaseActivity
import com.skott.softsquared.outsourcing_simulation.src.main.collect_user.model.CollectUserResponse

class CollectUserActivity:BaseActivity<CollectUserSaleProductLayoutBinding>(CollectUserSaleProductLayoutBinding::inflate),CollectUserView {
    private lateinit var context: Context
    private lateinit var adapter:CollectUseAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context=this
    }

    override fun onGetCollectUserSuccess(collectUserResponseArray: ArrayList<CollectUserResponse>) {

        binding.collectUserSaleProductRecyclerView.layoutManager = GridLayoutManager(context,2).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return 1
                }
            }
        }
        adapter=CollectUseAdapter(context,collectUserResponseArray)
        binding.collectUserSaleProductRecyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onGetCollectUserFailure(message: String) {
        Log.e("api error",message)
    }
}