package com.skott.softsquared.outsourcing_simulation.src.main.sold_product_list.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class SoldProductListResponse(
    val dong: String,
    val idx: Int,
    val isOnTop: String,
    val numOfChats: Int,
    val numOfLikes: Int,
    val passedTime: String,
    val pictureUrl: String?,
    val price: String,
    val status: String,
    val title: String
)
{
    override fun toString(): String {
        return Json.encodeToString(this)
    }
}