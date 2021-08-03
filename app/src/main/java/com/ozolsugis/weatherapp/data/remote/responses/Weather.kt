package com.ozolsugis.weatherapp.data.remote.responses

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)