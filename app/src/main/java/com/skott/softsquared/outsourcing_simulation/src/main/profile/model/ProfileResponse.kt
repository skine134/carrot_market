package com.skott.softsquared.outsourcing_simulation.src.main.profile.model

data class ProfileResponse(
    val answerRate: Double,
    val answerSpeed: String,
    val idx: Int,
    val mannerTemperature: Double,
    val nickName: String,
    val numOfBadges: Int,
    val numOfGoodDeals: Int,
    val numOfItems: Int,
    val numOfTotalDeals: Int,
    val reDealHopeRate: Double,
    val recentActivePeriod: String,
    val registerDate: String,
    val userLocation: ArrayList<UserLocation>
)