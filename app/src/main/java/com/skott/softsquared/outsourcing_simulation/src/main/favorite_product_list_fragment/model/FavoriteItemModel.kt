package com.skott.softsquared.outsourcing_simulation.src.main.favorite_product_list_fragment.model

data class FavoriteItemModel(
    val dong: String,
    val idx: Int,
    val isOnTop: String,
    val numOfChats: Int,
    val numOfLikes: Int,
    val passedTime: String,
    val pictureUrl: Any?,
    val price: String,
    val status: String,
    val title: String,
    val isliked:String
)
