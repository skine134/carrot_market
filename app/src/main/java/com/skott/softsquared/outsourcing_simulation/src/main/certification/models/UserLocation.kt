package com.skott.softsquared.outsourcing_simulation.src.main.certification.models

data class UserLocation(
    val dong: String,
    val idx: Int,
    val isAuthorized: String,
    val isCurrent: String,
    val villageIdx: Int,
    val villageRangeLevel: Int
)