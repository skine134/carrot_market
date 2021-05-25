package com.skott.softsquared.outsourcing_simulation.src.util.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.skott.softsquared.outsourcing_simulation.src.main.fragments.*

class HomePaerAdapter(fragmentManager: FragmentManager) :FragmentPagerAdapter(fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    override fun getCount()=5

    override fun getItem(position: Int): Fragment {
        return when(position)
        {
            0 -> HomeFragment()
            1 -> NeighborNewsFragment()
            2 -> NearByFragment()
            3 -> ChatFragment()
            4 -> MyCarrotFragment()
            else -> Fragment()
        }
    }
}