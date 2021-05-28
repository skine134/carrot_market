package com.skott.softsquared.outsourcing_simulation.src.splash.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class AutoSignInResponse(
    val dong: String,
    val idx: Int,
    val isAuthorized: String,
    val villageIdx: Int,
    val villageRangeLevel: Int
)
{

    override fun toString():String
    {
        return Json.encodeToString(this)
    }
}