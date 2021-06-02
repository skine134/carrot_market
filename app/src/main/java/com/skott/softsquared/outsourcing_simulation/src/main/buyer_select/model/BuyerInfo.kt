package com.skott.softsquared.outsourcing_simulation.src.main.buyer_select.model

import kotlinx.serialization.Serializable

@Serializable
data class BuyerInfo(
    val didReview: String,
    val idx: Int,
    val nickName: String,
    val profilePictureUrl: String?
)