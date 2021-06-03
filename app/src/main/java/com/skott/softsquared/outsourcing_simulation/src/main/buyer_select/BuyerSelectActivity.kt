package com.skott.softsquared.outsourcing_simulation.src.main.buyer_select

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.view.isGone
import androidx.recyclerview.widget.LinearLayoutManager
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.BuyerSelectLayoutBinding
import com.skott.softsquared.outsourcing_simulation.src.config.BaseActivity
import com.skott.softsquared.outsourcing_simulation.src.main.buyer_select.model.BuyerInfo
import com.skott.softsquared.outsourcing_simulation.src.main.buyer_select.model.SoldCommentIntentModel
import com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list.model.SaleProductListResponse
import com.skott.softsquared.outsourcing_simulation.src.main.sold_comment.SoldCommentActivity
import com.skott.softsquared.outsourcing_simulation.src.util.lib.getRoundedAllCornerBitmap
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class BuyerSelectActivity:BaseActivity<BuyerSelectLayoutBinding>(BuyerSelectLayoutBinding::inflate),BuyerSelectView {
    private lateinit var context: Context
    private lateinit var buyerAdapter : BuyerAdapter
    val service=BuyerSelectService(this)
    lateinit var productInfo:SaleProductListResponse
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context=this
        //intentTag 가 false이면 saleProduct에서 온것, true이면 soldProduct에서 온 것
        val intentTag = intent.getBooleanExtra(context.getString(R.string.buyer_select_activity_sold_or_sale_intent_key),false)
        binding.soldSuccessLayout.isGone=intentTag

        productInfo = Json.decodeFromString(
            intent.getStringExtra(context.getString(R.string.buyer_select_activity_product_intent_key))
                .toString()
        )
        var buyerList=ArrayList<BuyerInfo>()
        val usersInfo = intent.getStringExtra(context.getString(R.string.buyer_select_activity_intent_key)).toString()

        // 만약 사용자가 없으면 dump data를 넣는다.
        if(usersInfo.equals("null")||usersInfo.equals("")||usersInfo.equals("[]")) {
            buyerList.add(
                BuyerInfo(
                    "NO",
                    1,
                    "토끼마켓",
                    "https://igor-for-test-bucket.s3.ap-northeast-2.amazonaws.com/imageSample/keyboard1.jpeg"
                )
            )
        }
        else{
            buyerList= Json.decodeFromString(usersInfo)
        }
        val layoutManager = LinearLayoutManager(context)
        binding.buyerSelectorRecyclerView.layoutManager = layoutManager
        buyerAdapter = BuyerAdapter(context, buyerList)
        binding.buyerSelectorRecyclerView.adapter = buyerAdapter
        if(!productInfo.pictureUrl.toString().equals("")&&!productInfo.pictureUrl.toString().equals("null")) {
            getRoundedAllCornerBitmap(context,productInfo.pictureUrl.toString(),5,binding.productImageView)
        }
        binding.soldProductName.text=productInfo.title
        setFinishSoldActivity(binding.notCheckBuyerNowButton)
    }
    private fun setFinishSoldActivity(button: Button)
    {
        button.setOnClickListener{
            finish()
        }
    }
    override fun onBuyerSelectSuccess() {
        val intent = Intent(this,SoldCommentActivity::class.java)
        val buyer = buyerAdapter.arrayList[buyerAdapter.getClickItem()]
        intent.putExtra(context.getString(R.string.sold_comment_intent_key),SoldCommentIntentModel(buyer.idx,buyer.nickName,productInfo.idx,productInfo.title).toString())
        startActivity(intent)
    }

    override fun onBuyerSelectFailure(message: String) {
        Log.e("api error",message)
    }
}