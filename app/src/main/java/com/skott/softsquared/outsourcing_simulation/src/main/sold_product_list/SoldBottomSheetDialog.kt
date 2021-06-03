package com.skott.softsquared.outsourcing_simulation.src.main.sold_product_list

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.skott.softsquared.outsourcing_simulation.databinding.BottomDialogBinding

class SoldBottomSheetDialog(
    var soldEvent: View.OnClickListener?=null,
    var postUpdateEvent: View.OnClickListener?=null,
    var hideEvent: View.OnClickListener?=null,
    var deleteEvent: View.OnClickListener?=null
) : BottomSheetDialogFragment() {
    private lateinit var inflater: LayoutInflater
    private lateinit var binding: BottomDialogBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        this.inflater =
            requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = BottomDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.changeStatus.setOnClickListener(soldEvent?: View.OnClickListener { })
        binding.updatePost.setOnClickListener(postUpdateEvent?: View.OnClickListener { })
        binding.hidePost.setOnClickListener(hideEvent?: View.OnClickListener { })
        binding.deletePost.setOnClickListener(deleteEvent?: View.OnClickListener { })
    }

    @JvmName("setSoldEvent1")
    fun setSoldEvent(soldEvent: View.OnClickListener){
        this.soldEvent=soldEvent
    }
    @JvmName("setPostUpdateEvent1")
    fun setPostUpdateEvent(postUpdateEvent: View.OnClickListener){
        this.postUpdateEvent=postUpdateEvent
    }
    @JvmName("setHideEvent1")
    fun setHideEvent(hideEvent: View.OnClickListener){
        this.hideEvent=hideEvent
    }
    @JvmName("setDeleteEvent1")
    fun setDeleteEvent(deleteEvent: View.OnClickListener)
    {
        this.deleteEvent=deleteEvent
    }
}