package com.skott.softsquared.outsourcing_simulation.src.main.buyer_select.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class BuyerInfo(
    val didReview: String,
    val idx: Int,
    val nickName: String,
    val profilePictureUrl: String
)
{
    override fun toString(): String {
        return Json.encodeToString(this)
    }
}