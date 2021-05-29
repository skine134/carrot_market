package com.skott.softsquared.outsourcing_simulation.src.main.search.model

data class SearchPostRequest(
    var categories: ArrayList<String>,
    var lastItemIdx: String,
    var numOfPages: String,
    var rangeLevel: String,
    var villageIdx: String
)