package com.ozolsugis.weatherapp.data.remote

import com.ozolsugis.weatherapp.BuildConfig.API_KEY
import com.ozolsugis.weatherapp.data.remote.responses.WeatherForecast
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather?")
    suspend fun getWeather(
        @Query("lat") latitude : Double,
        @Query("lon") longitude : Double,
        @Query("units") units : String = "metric",
        @Query("lang") language : String = "en",
        @Query("appid") apiKey : String = API_KEY
    ): WeatherForecast
}