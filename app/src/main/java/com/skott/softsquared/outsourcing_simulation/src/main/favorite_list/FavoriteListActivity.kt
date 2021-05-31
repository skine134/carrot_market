package com.skott.softsquared.outsourcing_simulation.src.main.favorite_list

import android.content.Context
import android.os.Bundle
import android.widget.ImageButton
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.FavoriteListLayoutBinding
import com.skott.softsquared.outsourcing_simulation.src.config.BaseActivity
import com.skott.softsquared.outsourcing_simulation.src.main.favorite_product_list_fragment.FavoriteProductListView

class FavoriteListActivity:BaseActivity<FavoriteListLayoutBinding>(FavoriteListLayoutBinding::inflate) {
    private lateinit var context : Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        context=this
        val viewPagerAdapter = FavoritePagerAdapter(supportFragmentManager,binding.favoriteProductListTabLayout)
        setViewPagerEvent(binding.favoriteProductListViewPager,binding.favoriteProductListTabLayout)
        binding.favoriteProductListViewPager.adapter=viewPagerAdapter
        binding.favoriteProductListTabLayout.setupWithViewPager(binding.favoriteProductListViewPager)
        binding.favoriteProductListTabLayout.getTabAt(0)!!.text = context.getString(R.string.favorite_list_host_used_product)
        binding.favoriteProductListTabLayout.getTabAt(1)!!.text = context.getString(R.string.favorite_list_host_town_advertise)
        binding.favoriteProductListTabLayout.getTabAt(2)!!.text = context.getString(R.string.favorite_list_host_town_life)
        setBackButtonEvent(binding.favoriteProductListAppBar.getBackButton())

    }
    private fun setBackButtonEvent(button:ImageButton)
    {
        button.setOnClickListener{
            finish()
        }
    }
    private fun setViewPagerEvent(viewPager: ViewPager, tabLayout: TabLayout)
    {
        viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                tabLayout.getTabAt(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })
        tabLayout.addOnTabSelectedListener ( object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                var selectNum = -1
                when(tab!!.text)
                {
                    context.getString(R.string.favorite_list_host_used_product) -> {selectNum=0}
                    context.getString(R.string.favorite_list_host_town_advertise) -> {selectNum=1}
                    context.getString(R.string.favorite_list_host_town_life) -> {selectNum=2}
                    else -> return
                }
                viewPager.currentItem = selectNum
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        }
        )
    }
}