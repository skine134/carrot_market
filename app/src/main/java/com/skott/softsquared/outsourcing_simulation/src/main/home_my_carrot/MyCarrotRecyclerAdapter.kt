package com.skott.softsquared.outsourcing_simulation.src.main.home_my_carrot

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.MyCarrotRecyclerViewAdapterBinding
import com.skott.softsquared.outsourcing_simulation.src.main.home_my_carrot.model.MyCarrotListItem
import com.skott.softsquared.outsourcing_simulation.src.main.my_town_setting.MyTownSettingActivity

class MyCarrotRecyclerAdapter(val context: Context, val arrayList: ArrayList<MyCarrotListItem>) :RecyclerView.Adapter<MyCarrotRecyclerViewHolder>(){
    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding:MyCarrotRecyclerViewAdapterBinding
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MyCarrotRecyclerViewHolder {
        binding = MyCarrotRecyclerViewAdapterBinding.inflate(inflater,parent,false)
        setTownSettingIntentEvent(binding.root,arrayList[position])
        setAppSettingIntentEvent(binding.root,arrayList[position])
        return MyCarrotRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyCarrotRecyclerViewHolder, position: Int) {
        binding.itemImageView.setImageResource(arrayList[position].imageId)
        binding.itemContentTextView.text = arrayList[position].name
        holder.bind(binding.itemImageView,binding.itemContentTextView)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
    override fun getItemCount()=arrayList.count()
    private fun setTownSettingIntentEvent(itemLayout: ConstraintLayout,item: MyCarrotListItem)
    {
        if(item.name.equals(context.getString(R.string.my_carrot_my_town_setting)))
            itemLayout.setOnClickListener {
                val intent = Intent(context,MyTownSettingActivity::class.java)
                (context as Activity).startActivity(intent)
            }
    }
    private fun setAppSettingIntentEvent(itemLayout: ConstraintLayout,item: MyCarrotListItem)
    {
        if(item.name.equals(context.getString(R.string.my_carrot_app_setting)))
            itemLayout.setOnClickListener {
                val intent = Intent(context,MyTownSettingActivity::class.java)
                (context as Activity).startActivity(intent)
            }
    }
}