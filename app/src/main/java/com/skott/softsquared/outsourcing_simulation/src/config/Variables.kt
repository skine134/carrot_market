package com.skott.softsquared.outsourcing_simulation.src.config

import android.net.Uri

const val CERTIFICATIONS_TIME = 300
val ITEM_DEFAULT_URL_STRING = "item_default_image"
const val PRODUCT_LIST_PAGE_SIZE = 15
enum class TOWN_SCOPE{
    CLOSEST {
        override fun index() = 0
        override fun scope() = 1
        override fun seekVale() = 0
    },
    CLOSE {
        override fun index() = 1
        override fun scope() = 2
        override fun seekVale() = 33
    },
    FAR {
        override fun index() = 2
        override fun scope() = 4
        override fun seekVale() = 66
    },
    FARTHEST{
        override fun index() = 3
        override fun scope() = 5
        override fun seekVale() = 100
    };
    abstract fun index() : Int
    abstract fun scope() : Int
    abstract fun seekVale():Int
}