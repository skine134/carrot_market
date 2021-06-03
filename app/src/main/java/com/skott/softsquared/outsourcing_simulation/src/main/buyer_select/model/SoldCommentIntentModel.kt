package com.skott.softsquared.outsourcing_simulation.src.main.buyer_select.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
@Serializable
data class SoldCommentIntentModel(
    val buyerIdx: Int,
    val buyerName: String,
    val productIdx: Int,
    val productName: String
)
{
    override fun toString(): String {
        return Json.encodeToString(this)
    }
}