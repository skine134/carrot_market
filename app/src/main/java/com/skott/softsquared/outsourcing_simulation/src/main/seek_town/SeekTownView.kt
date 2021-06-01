package com.skott.softsquared.outsourcing_simulation.src.main.seek_town

import com.skott.softsquared.outsourcing_simulation.src.main.seek_town.model.TownResponse

interface SeekTownView {
    fun onGetTownsSuccess(townResponse: TownResponse)
    fun onGetTownsFailure(message:String)
}