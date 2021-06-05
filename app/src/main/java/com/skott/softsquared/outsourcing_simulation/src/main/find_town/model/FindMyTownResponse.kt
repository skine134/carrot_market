package com.skott.softsquared.outsourcing_simulation.src.main.find_town.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class FindMyTownResponse(
    val idx: Int,
    val village: String
)
{
    override fun toString(): String {
        return Json.encodeToString(this)
    }
}