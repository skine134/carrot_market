package com.skott.softsquared.outsourcing_simulation.src.main.home_near_by

import android.content.Context
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.NearByAdapterBinding
import com.skott.softsquared.outsourcing_simulation.src.main.home_near_by.model.NearbyPostModel
import com.skott.softsquared.outsourcing_simulation.src.util.lib.getRoundedAllCornerBitmap

class NearbyViewHolder(val context: Context, binding:NearByAdapterBinding):RecyclerView.ViewHolder(binding.root) {
    private val image=binding.image
    private val title=binding.contents
    private val profile=binding.profile
    private val town=binding.dong
    private val name=binding.name
    private val content=binding.contents
    fun bind(nearbyPostModel: NearbyPostModel)
    {
        getRoundedAllCornerBitmap(context,nearbyPostModel.image,5,image)

        val tmp = ShapeDrawable(OvalShape())
        tmp.paint.color = context.getColor(R.color.white)
        profile.background = tmp
        profile.clipToOutline = true
        getRoundedAllCornerBitmap(context,nearbyPostModel.profile,0,profile)
        title.text = nearbyPostModel.title
        town.text = nearbyPostModel.town
        name.text  = nearbyPostModel.name
        content.text = nearbyPostModel.content
    }
}