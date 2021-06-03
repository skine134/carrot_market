package com.skott.softsquared.outsourcing_simulation.src.main.buyer_select

interface BuyerSelectView {
    fun onBuyerSelectSuccess()
    fun onBuyerSelectFailure(message:String)
}