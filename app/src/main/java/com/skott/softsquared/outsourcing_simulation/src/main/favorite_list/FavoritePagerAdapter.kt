package com.skott.softsquared.outsourcing_simulation.src.main.favorite_list

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.material.tabs.TabLayout
import com.skott.softsquared.outsourcing_simulation.src.main.favorite_product_list.FavoriteProductListFragment
import com.skott.softsquared.outsourcing_simulation.src.main.favorite_town_life.FavoriteTownLifeFragment
import com.skott.softsquared.outsourcing_simulation.src.main.favorite_town_advertise.FavoriteAdvertiseFragment

class FavoritePagerAdapter(fragmentManager: FragmentManager,tapLayout: TabLayout) :
    FragmentStatePagerAdapter(fragmentManager){
    override fun getCount()=3

    override fun getItem(position: Int): Fragment {
        return when(position)
        {
            0 -> FavoriteProductListFragment()
            1 -> FavoriteAdvertiseFragment()
            2 -> FavoriteTownLifeFragment()
            else -> Fragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return (getItem(position) as BaseFavoriteListFragment).tabName
    }
}