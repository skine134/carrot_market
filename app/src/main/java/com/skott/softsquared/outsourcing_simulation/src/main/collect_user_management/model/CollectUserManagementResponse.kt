package com.skott.softsquared.outsourcing_simulation.src.main.collect_user_management.model

data class CollectUserManagementResponse(
    val dong: String,
    val isLiked: String,
    val nickName: String,
    val profilePictureUrl: String,
    val sellerIdx: Int,
    val siGunGu: String
)