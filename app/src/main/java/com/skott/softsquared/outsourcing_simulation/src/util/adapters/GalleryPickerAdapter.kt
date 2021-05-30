package com.skott.softsquared.outsourcing_simulation.src.util.adapters

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.databinding.GalleryPickedImageViewBinding
import com.skott.softsquared.outsourcing_simulation.src.util.lib.getRoundedAllCornerBitmap

class GalleryPickerViewHolder(val context: Context,binding:GalleryPickedImageViewBinding):RecyclerView.ViewHolder(binding.root)
{
    private val imageView: ImageView = binding.selectedImage
    fun bind(uriString: String)
    {
        getRoundedAllCornerBitmap(context,uriString,5,imageView)
    }
}
class GalleryPickerAdapter(val context: Context,val arrayList: ArrayList<String>):RecyclerView.Adapter<GalleryPickerViewHolder>()
{
    private val inflater=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
 private lateinit var binding:GalleryPickedImageViewBinding
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): GalleryPickerViewHolder {
        binding = GalleryPickedImageViewBinding.inflate(inflater,parent,false)
        binding.closeButton.setOnClickListener{
            arrayList.removeAt(position)
            notifyDataSetChanged()
        }
        return GalleryPickerViewHolder(context,binding)
    }

    override fun onBindViewHolder(holder: GalleryPickerViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    override fun getItemCount()=arrayList.size
    override fun getItemViewType(position: Int): Int {
        return position
    }
}