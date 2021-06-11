package com.skott.softsquared.outsourcing_simulation.src.main.home_chat

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.src.config.BaseFragment
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.ChatFragmentBinding
import com.skott.softsquared.outsourcing_simulation.src.main.home_chat.model.ChatModel
import com.skott.softsquared.outsourcing_simulation.src.util.lib.convertDpToPixel

class ChatFragment :
    BaseFragment<ChatFragmentBinding>(ChatFragmentBinding::bind, R.layout.chat_fragment) {
    private lateinit var adapter: ChatAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val itemDecoration = object :
            DividerItemDecoration(
                binding.chatRecyclerView.getRecyclerView().context,
                LinearLayoutManager(context).orientation
            ) {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                val margin = convertDpToPixel(requireContext(), 0).toInt()
                outRect.top = margin
                outRect.bottom = margin
            }
        }
        val arrayList = ArrayList<ChatModel>()
        dumpChatModel(arrayList)
        binding.chatRecyclerView.getRecyclerView().layoutManager = LinearLayoutManager(context)
        binding.chatRecyclerView.getRecyclerView().addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        adapter = ChatAdapter(requireContext(),arrayList)
        binding.chatRecyclerView.setAdapter(adapter)
            return binding.root
    }

    fun dumpChatModel(arrayList: ArrayList<ChatModel>) {
        arrayList.add(ChatModel("https://firebasestorage.googleapis.com/v0/b/softsquaredcarrotmarketc.appspot.com/o/image%2F03304ca5-b854-432d-bc73-79b19718f52e?alt=media&token=67a52dbc-3452-4f3e-a1dc-68d2f9f86c75",
        "스캇","다산 1동","6월 2일","생일 선물 주세요."))

        arrayList.add(ChatModel("https://firebasestorage.googleapis.com/v0/b/softsquaredcarrotmarketc.appspot.com/o/image%2F0fed6906-f556-4955-a849-171750a0f384?alt=media&token=96c7e8e7-8277-4c0a-8e25-c148f02767be",
            "수잔","다산 2동","2월 4일","네고 얼마나 되나요?"))

        arrayList.add(ChatModel("https://firebasestorage.googleapis.com/v0/b/softsquaredcarrotmarketc.appspot.com/o/image%2F54214fa9-0f46-4d02-85e1-3dbfa3566725?alt=media&token=cf5a62e4-578d-4ee0-8aa2-e1958f632a0a",
            "tysdf","상봉","5월 3일","어디로 갈까요?."))

        arrayList.add(ChatModel("https://firebasestorage.googleapis.com/v0/b/softsquaredcarrotmarketc.appspot.com/o/image%2F63503718-0b1e-4aa4-a6d2-745eca7da161?alt=media&token=142366fe-fe2b-468b-a1ca-f83b2a6e407f",
            "당근이","중동","3월 2일","취소 가능 할까요?"))

        arrayList.add(ChatModel("https://firebasestorage.googleapis.com/v0/b/softsquaredcarrotmarketc.appspot.com/o/image%2F757a401a-2256-4bda-989e-87a84781b4c0?alt=media&token=d0055bda-964d-4747-bbb6-663b1732de92",
            "김치","지금동","4월 24일","수료 가능 할까요?"))

    }
}