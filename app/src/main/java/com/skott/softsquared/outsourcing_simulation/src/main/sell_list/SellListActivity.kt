package com.skott.softsquared.outsourcing_simulation.src.main.sell_list

import android.content.Context
import android.os.Bundle
import android.widget.ImageButton
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.MySellActivityBinding
import com.skott.softsquared.outsourcing_simulation.src.config.BaseActivity

class SellListActivity:BaseActivity<MySellActivityBinding>(MySellActivityBinding::inflate) {
    private lateinit var context : Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        context=this
        val viewPagerAdapter = SellListPagerAdapter(supportFragmentManager,binding.sellListTabLayout)
        setViewPagerEvent(binding.sellListViewPager,binding.sellListTabLayout)
        binding.sellListViewPager.adapter=viewPagerAdapter
        binding.sellListTabLayout.setupWithViewPager(binding.sellListViewPager)
        binding.sellListTabLayout.getTabAt(0)!!.text = context.getString(R.string.sell_activity_host_sale)
        binding.sellListTabLayout.getTabAt(1)!!.text = context.getString(R.string.sell_activity_host_sold)
        binding.sellListTabLayout.getTabAt(2)!!.text = context.getString(R.string.sell_activity_host_hide)
        setBackButtonEvent(binding.sellListAppBar.getBackButton())

    }
    private fun setBackButtonEvent(button: ImageButton)
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
                    context.getString(R.string.sell_activity_host_sale) -> {selectNum=0}
                    context.getString(R.string.sell_activity_host_sold) -> {selectNum=1}
                    context.getString(R.string.sell_activity_host_hide) -> {selectNum=2}
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