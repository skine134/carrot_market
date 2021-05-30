package com.skott.softsquared.outsourcing_simulation.src.main.product_detail

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.ImageSliderViewBinding
import com.skott.softsquared.outsourcing_simulation.databinding.ProductDetailImageViewBinding
import com.skott.softsquared.outsourcing_simulation.src.util.lib.animToListView
import com.skott.softsquared.outsourcing_simulation.src.util.lib.getRoundedAllCornerBitmap

class ImageViewPagerAdapter(val context: Context,val imageArray:ArrayList<String>):PagerAdapter() {
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding:ProductDetailImageViewBinding
    override fun getCount()=imageArray.size
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view===`object`
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        binding = ProductDetailImageViewBinding.inflate(inflater,container,false)
        getRoundedAllCornerBitmap(context,imageArray[position],0,binding.image)
        container.addView(binding.root)
        return binding.root
    }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val v = `object` as View
        return vp.removeView(v)
    }
}