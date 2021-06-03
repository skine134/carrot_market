package com.skott.softsquared.outsourcing_simulation.src.main.collect_user_management

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.databinding.CollectUserManagementAdapterBinding
import com.skott.softsquared.outsourcing_simulation.src.main.collect_user_management.model.CollectUserManagementResponse
import com.skott.softsquared.outsourcing_simulation.src.util.lib.getRoundedAllCornerBitmap

class CollectUserManagementViewHolder(val context: Context, collectUserManagementAdapterBinding :CollectUserManagementAdapterBinding) : RecyclerView.ViewHolder(collectUserManagementAdapterBinding.root) {
    private val imageView=collectUserManagementAdapterBinding.userImageView
    private val nickname =collectUserManagementAdapterBinding.name
    private val address = collectUserManagementAdapterBinding.address
    fun bind(collectUserManagementResponse:CollectUserManagementResponse)
    {
        if(collectUserManagementResponse.profilePictureUrl!=null)
            getRoundedAllCornerBitmap(context,collectUserManagementResponse.profilePictureUrl,5,imageView)
        nickname.text = collectUserManagementResponse.nickName
        address.text = "${collectUserManagementResponse.siGunGu} ${collectUserManagementResponse.dong}"
    }
}