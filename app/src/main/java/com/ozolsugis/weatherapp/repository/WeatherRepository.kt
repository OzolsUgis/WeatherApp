package com.ozolsugis.weatherapp.repository

import com.ozolsugis.weatherapp.data.remote.WeatherApi
import com.ozolsugis.weatherapp.data.remote.responses.WeatherForecast
import com.ozolsugis.weatherapp.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class WeatherRepository @Inject constructor(
    private val api : WeatherApi
) {
    suspend fun getWeatherForecast(latitude : Double, longitude : Double) : Resource<WeatherForecast>{
        val response = try {
            api.getWeather(latitude, longitude)
        }catch (e : Exception){
            Timber.d(e)
            return Resource.Error("An unknown error occurred")
        }
        return Resource.Success(response)
    }
}