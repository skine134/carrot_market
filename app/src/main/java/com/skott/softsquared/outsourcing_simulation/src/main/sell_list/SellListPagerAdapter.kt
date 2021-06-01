package com.skott.softsquared.outsourcing_simulation.src.main.sell_list

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.material.tabs.TabLayout
import com.skott.softsquared.outsourcing_simulation.src.main.hide_product_list.HideProductListFragment
import com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list.SaleProductListFragment
import com.skott.softsquared.outsourcing_simulation.src.main.sold_product_list.SoldProductListFragment

class SellListPagerAdapter(fragmentManager: FragmentManager, tapLayout: TabLayout) :
    FragmentStatePagerAdapter(fragmentManager){
    override fun getCount()=3

    override fun getItem(position: Int): Fragment {
        return when(position)
        {
            0 -> SaleProductListFragment()
            1 -> SoldProductListFragment()
            2 -> HideProductListFragment()
            else -> Fragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return (getItem(position) as BaseSellFragment).tabName
    }
}