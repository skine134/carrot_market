package com.skott.softsquared.outsourcing_simulation.src.main.home_product_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.PleaseTownAuthFragmentBinding
import com.skott.softsquared.outsourcing_simulation.src.config.BaseFragment

class PleaseTownAuthFragment :BaseFragment<PleaseTownAuthFragmentBinding>(PleaseTownAuthFragmentBinding::bind,
    R.layout.please_town_auth_fragment){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        // TODO naver sdk api
//        setAuthStartIntentEvent()
        return binding.root
    }
}