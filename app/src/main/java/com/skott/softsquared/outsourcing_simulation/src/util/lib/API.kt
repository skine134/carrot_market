package com.skott.softsquared.outsourcing_simulation.src.util.lib

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.KClass


inline fun <reified T : Any> getAPIHandler(prefix: String, t: KClass<T>) : T
{
    val gson = GsonBuilder().setLenient().create()
    return Retrofit.Builder().baseUrl(prefix)
        .addConverterFactory(GsonConverterFactory.create(gson)).build().create(t.java)
}