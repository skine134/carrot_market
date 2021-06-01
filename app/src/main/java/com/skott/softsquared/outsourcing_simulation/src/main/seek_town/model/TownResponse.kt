package com.skott.softsquared.outsourcing_simulation.src.main.seek_town.model

import kotlinx.serialization.Serializable

@Serializable
data class TownResponse(
    val dong: String,
    val idx: Int,
    val isCurrent: String,
    val nearVillages: ArrayList<NearVillage>,
    val villageIdx: Int
)