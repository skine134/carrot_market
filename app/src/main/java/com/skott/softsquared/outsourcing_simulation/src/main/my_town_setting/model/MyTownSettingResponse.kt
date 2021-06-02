package com.skott.softsquared.outsourcing_simulation.src.main.my_town_setting.model

data class MyTownSettingResponse(
    val dong: String,
    val idx: Int,
    val isCurrent: String,
    val nearVillages: ArrayList<NearVillage>,
    val rangeLevel: Int,
    val villageIdx: Int
)