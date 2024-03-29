package com.skott.softsquared.outsourcing_simulation.src.main.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.skott.softsquared.outsourcing_simulation.src.main.home_product_list.*
import com.skott.softsquared.outsourcing_simulation.src.main.home_chat.ChatFragment
import com.skott.softsquared.outsourcing_simulation.src.main.home_my_carrot.MyCarrotFragment
import com.skott.softsquared.outsourcing_simulation.src.main.home_near_by.NearByFragment
import com.skott.softsquared.outsourcing_simulation.src.main.home_neighbor_news.NeighborNewsFragment

class HomePaerAdapter(fragmentManager: FragmentManager) :FragmentPagerAdapter(fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    override fun getCount()=5

    override fun getItem(position: Int): Fragment {
        return when(position)
        {
            0 -> ProductListFragment()
            1 -> NeighborNewsFragment()
            2 -> NearByFragment()
            3 -> ChatFragment()
            4 -> MyCarrotFragment()
            else -> Fragment()
        }
    }
}