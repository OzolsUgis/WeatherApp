package com.ozolsugis.weatherapp.presentation.homepage

import androidx.lifecycle.ViewModel
import com.ozolsugis.weatherapp.data.remote.responses.WeatherForecast
import com.ozolsugis.weatherapp.repository.WeatherRepository
import com.ozolsugis.weatherapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherAppHomePageViewModel @Inject constructor(
    private val repository: WeatherRepository
): ViewModel() {

    suspend fun  getWeatherForecast(
        latitude : Double,
        longitude : Double
    ): Resource<WeatherForecast>{
        return repository.getWeatherForecast(latitude, longitude)
    }
}