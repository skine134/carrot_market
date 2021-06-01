package com.skott.softsquared.outsourcing_simulation.src.main.buy_list.model

data class BuyListResponse(
    val dong: String,
    val idx: Int,
    val isOnTop: String,
    val numOfChats: Int,
    val numOfLikes: Int,
    val passedTime: String,
    val pictureUrl: Any?,
    val price: String,
    val status: String,
    val title: String
)
