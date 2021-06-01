package com.skott.softsquared.outsourcing_simulation.src.main.buy_list

import com.skott.softsquared.outsourcing_simulation.src.main.buy_list.model.BuyListResponse

interface BuyListView {
    fun onGetBuyListSuccess(buyListResponseArray: ArrayList<BuyListResponse>)
    fun onGetBuyListFailure(message:String)
}