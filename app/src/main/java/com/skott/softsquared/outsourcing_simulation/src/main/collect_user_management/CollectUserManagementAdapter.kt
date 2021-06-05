package com.skott.softsquared.outsourcing_simulation.src.main.collect_user_management

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.CollectUserManagementAdapterBinding
import com.skott.softsquared.outsourcing_simulation.src.main.collect_user_management.model.CollectUserManagementResponse

class CollectUserManagementAdapter(val context: Context,val arrayList: ArrayList<CollectUserManagementResponse>,val service: CollectUserManagementService):RecyclerView.Adapter<CollectUserManagementViewHolder>() {
    private val inflate=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private var clickPosition=-1
    private lateinit var binding: CollectUserManagementAdapterBinding
    override fun onCreateViewHolder(
        parent: ViewGroup,
        position: Int
    ): CollectUserManagementViewHolder {
        binding = CollectUserManagementAdapterBinding.inflate(inflate,parent,false)
        binding.collectUserButton.setOnClickListener {
            service.tryPostCollectUser(arrayList[position].sellerIdx)
            if(binding.collectUserButton.isChecked)
            {

                binding.collectUserButton.setTextColor(context.getColor(R.color.white))
                binding.collectUserButton.text = context.getString(R.string.collect_user_management_enable_button)
            }else
            {   binding.collectUserButton.setTextColor(context.getColor(R.color.black))
                binding.collectUserButton.text = context.getString(R.string.collect_user_management_disable_button)
            }
        }
        return CollectUserManagementViewHolder(context,binding)
    }

    override fun onBindViewHolder(holder: CollectUserManagementViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    override fun getItemCount()=arrayList.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun getClickItem(): Int
    {
        return clickPosition
    }
}