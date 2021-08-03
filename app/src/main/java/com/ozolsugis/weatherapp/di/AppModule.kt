package com.ozolsugis.weatherapp.di

import com.ozolsugis.weatherapp.data.remote.WeatherApi
import com.ozolsugis.weatherapp.repository.WeatherRepository
import com.ozolsugis.weatherapp.util.Constants.BASE_URL
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

object AppModule {
    @Singleton
    @Provides
    fun provideWeatherRepository(
        api : WeatherApi
    )= WeatherRepository(api)

    @Singleton
    @Provides
    fun provideWeatherApi(): WeatherApi{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(WeatherApi::class.java)
    }

}