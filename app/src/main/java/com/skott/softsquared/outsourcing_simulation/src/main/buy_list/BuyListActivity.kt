package com.skott.softsquared.outsourcing_simulation.src.main.buy_list

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.databinding.MyBuyListLayoutBinding
import com.skott.softsquared.outsourcing_simulation.src.config.BaseActivity
import com.skott.softsquared.outsourcing_simulation.src.main.buy_list.model.BuyListResponse
import com.skott.softsquared.outsourcing_simulation.src.util.lib.convertDpToPixel

class BuyListActivity:BaseActivity<MyBuyListLayoutBinding>(MyBuyListLayoutBinding::inflate),BuyListView {
    private lateinit var context:Context
    private lateinit var adapter:BuyListAdapter
    private lateinit var buyListService: BuyListService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context=this
        buyListService = BuyListService(this)
        val itemDecoration=object: RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                val margin = convertDpToPixel(context,6).toInt()
                outRect.top = margin
                outRect.bottom = margin
            }
        }
        binding.buyListRecyclerView.getRecyclerView().addItemDecoration(itemDecoration)
        setBackButtonEvent(binding.myBuyListAppBar.getBackButton())
    }

    override fun onStart() {
        super.onStart()
        buyListService.tryGetBuyList()
    }
    private fun setBackButtonEvent(button:ImageButton)
    {
        button.setOnClickListener{
            finish()
        }
    }
    override fun onGetBuyListSuccess(buyListResponseArray: ArrayList<BuyListResponse>) {
        adapter = BuyListAdapter(context,buyListResponseArray)
        binding.buyListRecyclerView.setAdapter(adapter)
    }

    override fun onGetBuyListFailure(message: String) {
        Log.e("api error",message)
    }
}