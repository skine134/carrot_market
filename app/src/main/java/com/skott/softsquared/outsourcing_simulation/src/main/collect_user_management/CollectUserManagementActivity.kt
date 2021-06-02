package com.skott.softsquared.outsourcing_simulation.src.main.collect_user_management

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.CollectUserManagementLayoutBinding
import com.skott.softsquared.outsourcing_simulation.src.config.BaseActivity
import com.skott.softsquared.outsourcing_simulation.src.main.collect_user_management.model.CollectUserManagementResponse

class CollectUserManagementActivity:BaseActivity<CollectUserManagementLayoutBinding>(CollectUserManagementLayoutBinding::inflate),CollectUserManagementView {
    private val context=this
    private val service=CollectUserManagementService(this)
    private lateinit var adapter: CollectUserManagementAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        service.tryGetCollectUserList()
    }


    override fun onGetCollectUserListSuccess(collectUserManagementResponseArray: ArrayList<CollectUserManagementResponse>) {
        adapter = CollectUserManagementAdapter(context,collectUserManagementResponseArray,service)
        binding.collectUserManagementRecyclerView.layoutManager=LinearLayoutManager(context)
        binding.collectUserManagementRecyclerView.adapter=adapter
        adapter.notifyDataSetChanged()
    }

    override fun onGetCollectUserListFailure(message: String) {
        Log.e("api error",message)
    }

    override fun onPostCollectUserSuccess() {
        showCustomToast(context.getString(R.string.collect_user_management_delete_message))
    }

    override fun onPostCollectUserFailure(message: String) {
        Log.e("api error",message)
    }
}