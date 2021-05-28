package com.skott.softsquared.outsourcing_simulation.src.main.home_product_list.model

data class ProductListResponse(
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