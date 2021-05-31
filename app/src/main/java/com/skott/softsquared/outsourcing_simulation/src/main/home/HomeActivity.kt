package com.skott.softsquared.outsourcing_simulation.src.main.home

import android.os.Bundle
import android.view.MenuItem
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.skott.softsquared.outsourcing_simulation.src.config.BaseActivity
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.HomeLayoutBinding

class HomeActivity : BaseActivity<HomeLayoutBinding>(HomeLayoutBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.mainContentViewPager.adapter= HomePaerAdapter(supportFragmentManager)
        binding.mainContentViewPager.offscreenPageLimit=5
        setViewPagerEvent(binding.mainContentViewPager,binding.homeBottomNavView)
    }
    private fun setViewPagerEvent(viewPager:ViewPager,bottomNavigationView: BottomNavigationView)
    {
        viewPager.addOnPageChangeListener(object:ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(position: Int) {
                bottomNavigationView.menu.getItem(position).isChecked=true
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })
        bottomNavigationView.setOnNavigationItemSelectedListener ( object:BottomNavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                var selectNum = -1
                when(item.itemId)
                {
                    R.id.main_home_item -> {selectNum=0}
                    R.id.main_neighborhood_news_item -> {selectNum=1}
                    R.id.main_near_by_item -> {selectNum=2}
                    R.id.main_chat_item -> {selectNum=3}
                    R.id.main_my_carrot_item -> {selectNum=4}
                    else -> return false
                }
                viewPager.currentItem = selectNum
                return true
            }
        }
        )
    }
}