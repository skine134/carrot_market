package com.skott.softsquared.outsourcing_simulation.src.main.find_town

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.FindTownByCurrentLocationBinding
import com.skott.softsquared.outsourcing_simulation.src.config.BaseActivity
import com.skott.softsquared.outsourcing_simulation.src.main.create_profile.CreateProfileActivity
import com.skott.softsquared.outsourcing_simulation.src.main.find_town.model.FindMyTownResponse
import com.skott.softsquared.outsourcing_simulation.src.main.find_town.model.RegisterAddressRequest
import com.skott.softsquared.outsourcing_simulation.src.util.lib.convertDpToPixel

class FindTownActivity :BaseActivity<FindTownByCurrentLocationBinding>(FindTownByCurrentLocationBinding::inflate),FindMyTownView{
    private lateinit var context: Context
    private lateinit var adapter:FindMyTownAdapter
    private var intentValue = -1
    private lateinit var findTownService:FindMyTownService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        findTownService = FindMyTownService(this)
        intentValue = intent.getIntExtra(context.getString(R.string.my_carrot_find_my_address_intent_key),-1)
        setSearchEvent(binding.searchTownByLocation.getEditText())
        val itemDecoration=object:
            DividerItemDecoration(binding.findTownRecyclerMessageView.context,LinearLayoutManager(context).orientation){
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                val margin = convertDpToPixel(context,6).toInt()
                outRect.top = margin
                outRect.bottom = margin
            }
        }
        binding.findTownRecyclerMessageView.getRecyclerView().addItemDecoration(itemDecoration)
    }
    private fun searchAddress(editText: EditText)
    {
        findTownService.tryGetTown(editText.text.toString())
    }
    private fun setSearchEvent(editText: EditText)
    {
        editText.addTextChangedListener{
            searchAddress(editText)
        }
    }
    override fun onGetSearchTownSuccess(findMyTownResponseArray: ArrayList<FindMyTownResponse>) {

        adapter = FindMyTownAdapter(context,findMyTownResponseArray,findTownService)
        binding.findTownRecyclerMessageView.setAdapter(adapter)
    }

    override fun onGetSearchTownFailure(message: String) {
        showCustomToast(message)
    }

    override fun onPostRegisterAddressSuccess() {
        // 일반 동네 등록
        if(intentValue==-1)
        {
            finish()
            return
        }
        // 동네 모두 삭제 후 동네 등록
        findTownService.tryPatchDeleteAddress(RegisterAddressRequest(intentValue))
    }
    fun getIntentVale(): Int {
        return intentValue
    }
    override fun onPatchDeleteAddressSuccess() {
        finish()
    }

    override fun onPostRegisterAddressFailure(message: String) {
        Log.e("api error",message)
    }

    override fun onPatchDeleteAddressFailure(message: String) {
        Log.e("api error",message)
    }

}