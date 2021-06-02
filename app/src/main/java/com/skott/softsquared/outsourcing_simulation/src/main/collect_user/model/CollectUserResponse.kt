package com.skott.softsquared.outsourcing_simulation.src.main.collect_user.model

data class CollectUserResponse(
    val dong: String,
    val idx: Int,
    val nickName: String,
    val numOfChats: Int,
    val numOfLikes: Int,
    val pictureUrl: String?,
    val price: Int,
    val siGunGu: String,
    val status: String,
    val title: String
)