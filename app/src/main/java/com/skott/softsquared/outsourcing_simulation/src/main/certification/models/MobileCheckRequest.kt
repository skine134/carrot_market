package com.skott.softsquared.outsourcing_simulation.src.main.certification.models

data class MobileCheckRequest(
    val mobile: String,
    val verificationCode: String
)