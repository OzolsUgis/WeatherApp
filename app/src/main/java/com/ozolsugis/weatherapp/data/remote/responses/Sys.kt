package com.ozolsugis.weatherapp.data.remote.responses

data class Sys(
    val country: String,
    val id: Int,
    val message: Double,
    val sunrise: Long,
    val sunset: Long,
    val type: Int
)