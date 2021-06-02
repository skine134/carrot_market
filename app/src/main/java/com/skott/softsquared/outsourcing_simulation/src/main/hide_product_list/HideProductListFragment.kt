package com.skott.softsquared.outsourcing_simulation.src.main.hide_product_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skott.softsquared.outsourcing_simulation.src.main.sell_list.BaseSellFragment

class HideProductListFragment:BaseSellFragment() {

    override var tabName="숨김"
    override var emptyMessage="아직 숨긴 리스트가 없습니다."
    override var service: () -> Unit={}
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }
}