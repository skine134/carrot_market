package com.skott.softsquared.outsourcing_simulation.src.main.create_profile.models

import com.skott.softsquared.outsourcing_simulation.src.main.gallery_picker.model.Picture

data class SignUpRequest(
    val mobile: String,
    val nickname: String,
    val userImageUrl: Picture
)