package com.skott.softsquared.outsourcing_simulation.src.main.sale_product_list.model

import kotlinx.serialization.Serializable

@Serializable
data class SoldResponse(
    val didReview: String,
    val idx: Int,
    val nickName: String,
    val profilePictureUrl: String?
)