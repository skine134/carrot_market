package com.skott.softsquared.outsourcing_simulation.src.main.buyer_select

import android.content.Context
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.BuyerSelectLayoutBinding
import com.skott.softsquared.outsourcing_simulation.src.config.BaseActivity
import com.skott.softsquared.outsourcing_simulation.src.main.buyer_select.model.BuyerInfo
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class BuyerSelectActivity:BaseActivity<BuyerSelectLayoutBinding>(BuyerSelectLayoutBinding::inflate) {
    private lateinit var context: Context
    private lateinit var buyerAdapter : BuyerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context=this
        val intentValue = intent.getStringExtra(context.getString(R.string.buyer_select_activity_intent_key)).toString()
        if(!intentValue.equals("")&&!intentValue.equals("null")) {
            val buyerList: ArrayList<BuyerInfo> = Json.decodeFromString(intentValue)
            buyerAdapter = BuyerAdapter(context, buyerList)
            val layoutManager = LinearLayoutManager(context)
            binding.buyerSelectorRecyclerView.layoutManager = layoutManager
            binding.buyerSelectorRecyclerView.adapter = buyerAdapter
        }
        setFinishSoldActivity(binding.notCheckBuyerNowButton)
    }
    private fun setFinishSoldActivity(button: Button)
    {
        button.setOnClickListener{
            finish()
        }
    }
}