package com.skott.softsquared.outsourcing_simulation.src.main.sell_list

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.MySellFragmentBinding
import com.skott.softsquared.outsourcing_simulation.src.config.BaseFragment
import com.skott.softsquared.outsourcing_simulation.src.util.lib.convertDpToPixel

abstract class BaseSellFragment  : BaseFragment<MySellFragmentBinding>(
    MySellFragmentBinding::bind,
    R.layout.my_sell_fragment
) {
    //    abstract var adapter: RecyclerView.Adapter<VH>
    abstract var tabName: String
    abstract var emptyMessage: String
    abstract var service: () -> Unit
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.mySellRecyclerMessageView.message = emptyMessage
        val itemDecoration = object :RecyclerView.ItemDecoration()
        {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                val margin = convertDpToPixel(requireContext(),6).toInt()
                outRect.top = margin
                outRect.bottom = margin
            }
        }

        binding.mySellRecyclerMessageView.getRecyclerView().addItemDecoration(itemDecoration)

//        binding.favoriteList.getRecyclerView().addOnScrollListener(getScrollListener {
//            service()
//        })
        return binding.root
    }

    override fun onStart() {
        service()
        super.onStart()
    }
}