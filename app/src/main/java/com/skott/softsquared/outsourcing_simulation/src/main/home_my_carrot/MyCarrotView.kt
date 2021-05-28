package com.skott.softsquared.outsourcing_simulation.src.main.home_my_carrot

import com.skott.softsquared.outsourcing_simulation.src.main.home_my_carrot.model.MyCarrotResponse

interface MyCarrotView {
    fun onGetMyCarrotSuccess(response: MyCarrotResponse)
    fun onGetMyCarrotFailure(message:String)
}