package com.ozolsugis.weatherapp.util

import android.graphics.Color
import com.ozolsugis.weatherapp.R
import com.ozolsugis.weatherapp.presentation.ui.theme.*

object Constants {

    const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    const val LOCATION_PERMISSION_REQUEST_CODE = 0
    const val LOCATION_REQUEST_INTERVAL : Long = 0
    const val LOCATION_REQUEST_FASTEST_INTERVAL : Long = 0
    const val LOCATION_REQUEST_NUM_UPDATES = 1
    const val APPLICATION_NAME = "Current Weather"

    // Weather  indication ID lists
    val THUNDERSTORM_LIST = listOf<Int>(200,201,202,210,211,212,221,230,231,232)
    val RAIN_LIST = listOf<Int>(300,301,32,310,311,312,313,314,321,500,501,502,503,504,511,520,521,522,531)
    val SNOW_LIST = listOf<Int>(600,601,602,611,612,613,615,616,620,621,622)
    val CLEARSKY_LIST = listOf<Int>(800)
    val CLOUDS_LIST = listOf<Int>(801,802,803,804)

    // Weather Icons
    const val BROKEN_CLOUDS_ICON = R.drawable.broken_clouds
    const val CLEAR_SKY_DAY_ICON = R.drawable.clear_day
    const val CLEAR_SKY_NIGHT_ICON = R.drawable.clear_night
    const val CLOUDS_DAY_ICON = R.drawable.cloudy_day
    const val FEW_CLOUDS_DAY_ICON = R.drawable.cloudy_day
    const val FEW_CLOUDS_NIGHT_ICON = R.drawable.cloudy_night
    const val MIST_ICON = R.drawable.mist
    const val RAIN_DAY_ICON = R.drawable.rain_day
    const val RAIN_FULL_OF_CLOUDS_ICON = R.drawable.rain
    const val RAIN_NIGHT_ICON = R.drawable.rain_night
    const val SNOW_ICON = R.drawable.snow
    const val THUNDERSTORM_ICON = R.drawable.thunderstorm

    // Weather Icon id's from http call response
    val BROKEN_CLOUDS = listOf("04d","04n")
    const val CLEAR_SKY_DAY ="01d"
    const val CLEAR_SKY_NIGHT = "01n"
    val CLOUDS_DAY = listOf("03d","03n")
    const val FEW_CLOUDS_DAY = "02d"
    const val FEW_CLOUDS_NIGHT = "02n"
    val MIST = listOf("50d","50n")
    const val RAIN_DAY = "10d"
    val RAIN_FULL_OF_CLOUDS = listOf("09d","09n")
    const val RAIN_NIGHT = "10n"
    val SNOW = listOf("13d","13n")
    val THUNDERSTORM = listOf("11d","11n")

    // Support icons
    const val COMPASS= R.drawable.compass
    const val WIND_SPEED = R.drawable.wind
    const val SUNRISE = R.drawable.sunrise
    const val SUNSET = R.drawable.sunset

    // Weather Background color lists
    //Day
    val THUNDERSTORM_BACKGROUND_DAY = listOf(CleanBlue, ThunderBlueDay, MediumGray)
    val RAIN_BACKGROUND_DAY  = listOf(LightGray, CleanBlue)
    val SNOW_BACKGROUND_DAY = listOf(LightGray, SnowWhite)
    val CLEAR_BACKGROUND_DAY = listOf(DayMediumBlue,DayBlue,DayBlue,DayBlue, DaySun)
    val CLOUDS_BACKGROUND_DAY = listOf(CloudsDayMedium, CloudsDayLight)
    //Night
    val THUNDERSTORM_BACKGROUND_NIGHT = listOf(ThunderGrayDay,  MediumGray)
    val RAIN_BACKGROUND_NIGHT = listOf(MediumGray,MediumBlue)
    val SNOW_BACKGROUND_NIGHT = listOf(MediumGray,  SnowWhiteNight)
    val CLEAR_BACKGROUND_NIGHT = listOf(NightDark, NightMedium)
    val CLOUDS_BACKGROUND_NIGHT = listOf(CloudsNightDark, CloudsNightLight)





}