package com.skott.softsquared.outsourcing_simulation.src.main.product_detail.model

import com.skott.softsquared.outsourcing_simulation.src.main.gallery_picker.model.Picture
import kotlinx.serialization.Serializable

@Serializable
data class ProductDetailResponse(
    val category: String,
    val chatNum: Int,
    val clickNum: Int,
    val content: String,
    val dong: String,
    val idx: Int,
    val isLiked: String,
    val isNegotiable: String,
    val isOnTop: String,
    val likeNum: Int,
    val passedTime: String,
    val Pictures: ArrayList<Picture>,
    val price: String,
    val recommendedItems: ArrayList<SmallProduct>,
    val sellerIdx: Int,
    val sellerItems: ArrayList<SmallProduct>,
    val sellerMannerTemperature: Double,
    val sellerNickname: String,
    val status: String,
    val title: String,
    val userNickname: String
)