package com.skott.softsquared.outsourcing_simulation.src.main.sold_comment

import android.os.Bundle
import androidx.core.view.isGone
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.SoldCommentLayoutBinding
import com.skott.softsquared.outsourcing_simulation.src.config.BaseActivity
import com.skott.softsquared.outsourcing_simulation.src.main.buyer_select.model.SoldCommentIntentModel
import com.skott.softsquared.outsourcing_simulation.src.util.custom_views.IconTextView
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class SoldCommentActivity :
    BaseActivity<SoldCommentLayoutBinding>(SoldCommentLayoutBinding::inflate) {
    private lateinit var clickSticker: IconTextView
    private val context = this
    private lateinit var soldInfo: SoldCommentIntentModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        soldInfo = Json.decodeFromString(
            intent.getStringExtra(context.getString(R.string.sold_comment_intent_key)).toString()
        )
        binding.soldOutProductName.text = soldInfo.productName
        binding.howDoSold.text =
            context.getString(R.string.how_do_sold).replace("name", soldInfo.buyerName)
        binding.badSticker.setOnClickListener {
            setCommentActiveEvent(binding.badSticker)
        }

        binding.normalSticker.setOnClickListener {
            setCommentActiveEvent(binding.normalSticker)
        }

        binding.greatSticker.setOnClickListener {
            setCommentActiveEvent(binding.greatSticker)
        }
    }

    private fun setCommentActiveEvent(sticker: IconTextView) {
        clickSticker = sticker
        val isBadComment = clickSticker == binding.badSticker
        binding.goodContent.isGone = isBadComment
        binding.badContent.isGone = !isBadComment
        binding.greatSticker.getTextView()
            .setTextColor(context.getColor(R.color.sold_comment_sticker_text_bg))
        binding.normalSticker.getTextView()
            .setTextColor(context.getColor(R.color.sold_comment_sticker_text_bg))
        binding.badSticker.getTextView()
            .setTextColor(context.getColor(R.color.sold_comment_sticker_text_bg))
        binding.badSticker.getImageView().setImageResource(R.drawable.bad_disable)
        binding.normalSticker.getImageView().setImageResource(R.drawable.normal_disable)
        binding.greatSticker.getImageView().setImageResource(R.drawable.great_disable)

        if (isBadComment) {
            binding.badSticker.getImageView().setImageResource(R.drawable.bad_enable)
            binding.badSticker.getTextView()
                .setTextColor(context.getColor(R.color.sold_comment_bad_sticker_text_bg))
        } else if (clickSticker == binding.normalSticker) {

            binding.normalSticker.getImageView().setImageResource(R.drawable.normal_enable)
            binding.normalSticker.getTextView()
                .setTextColor(context.getColor(R.color.sold_comment_normal_sticker_text_bg))
        } else {
            binding.greatSticker.getImageView().setImageResource(R.drawable.great_enable)
            binding.greatSticker.getTextView()
                .setTextColor(context.getColor(R.color.sold_comment_great_sticker_text_bg))
        }

    }
}