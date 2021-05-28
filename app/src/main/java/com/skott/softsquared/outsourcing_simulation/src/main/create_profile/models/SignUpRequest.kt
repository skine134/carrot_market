package com.skott.softsquared.outsourcing_simulation.src.main.create_profile.models

data class SignUpRequest(
    val mobile: String,
    val nickname: String,
    val userImageUrl: String
)