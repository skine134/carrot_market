package com.skott.softsquared.outsourcing_simulation.src

data class BaseModel<M>(val isSuccess: Boolean, val code: Int, val message: String, val result: M)