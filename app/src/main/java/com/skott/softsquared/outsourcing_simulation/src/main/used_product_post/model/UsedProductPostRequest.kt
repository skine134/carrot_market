package com.skott.softsquared.outsourcing_simulation.src.main.used_product_post.model

import com.skott.softsquared.outsourcing_simulation.src.main.gallery_picker.model.Picture

data class UsedProductPostRequest(
    var title: String,
    var category: String,
    var price: Int?,
    var isNegotiable: String = "YES",
    var content: String,
    var villageIdx: Int?,
    var rangeLevel: Int?,
    var Pictures: ArrayList<Picture>?,
)