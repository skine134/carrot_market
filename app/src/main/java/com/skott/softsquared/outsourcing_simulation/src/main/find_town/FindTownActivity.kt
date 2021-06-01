package com.skott.softsquared.outsourcing_simulation.src.main.find_town

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.databinding.FindTownByCurrentLocationBinding
import com.skott.softsquared.outsourcing_simulation.src.config.BaseActivity
import com.skott.softsquared.outsourcing_simulation.src.main.find_town.model.FindMyTownResponse
import com.skott.softsquared.outsourcing_simulation.src.util.lib.convertDpToPixel

class FindTownActivity :BaseActivity<FindTownByCurrentLocationBinding>(FindTownByCurrentLocationBinding::inflate),FindMyTownView{
    private lateinit var context: Context
    private lateinit var adapter:FindMyTownAdapter
    private lateinit var findTownService:FindMyTownService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        findTownService = FindMyTownService(this)
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
    private fun setSearchEvent(editText: EditText)
    {
        editText.setOnKeyListener{ view: View, keyCode: Int, keyEvent: KeyEvent ->
            if(keyCode==KeyEvent.KEYCODE_ENTER) {
                findTownService.tryGetTown(editText.text.toString())
                return@setOnKeyListener false
            }
            return@setOnKeyListener false
        }
    }
    override fun onGetSearchTownSuccess(findMyTownResponseArray: ArrayList<FindMyTownResponse>) {

        adapter = FindMyTownAdapter(context,findMyTownResponseArray,binding.findTownRecyclerMessageView)
        binding.findTownRecyclerMessageView.getRecyclerView().layoutManager = LinearLayoutManager(context)
        binding.findTownRecyclerMessageView.getRecyclerView().adapter = adapter
    }

    override fun onGetSearchTownFailure(message: String) {
        showCustomToast(message)
    }

}