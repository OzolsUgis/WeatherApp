package com.ozolsugis.weatherapp.util

import androidx.compose.ui.graphics.Color
import com.ozolsugis.weatherapp.util.Constants.CLEAR_BACKGROUND_DAY
import com.ozolsugis.weatherapp.util.Constants.CLEAR_BACKGROUND_NIGHT
import com.ozolsugis.weatherapp.util.Constants.CLOUDS_BACKGROUND_DAY
import com.ozolsugis.weatherapp.util.Constants.CLOUDS_BACKGROUND_NIGHT
import com.ozolsugis.weatherapp.util.Constants.RAIN_BACKGROUND_DAY
import com.ozolsugis.weatherapp.util.Constants.RAIN_BACKGROUND_NIGHT
import com.ozolsugis.weatherapp.util.Constants.SNOW_BACKGROUND_DAY
import com.ozolsugis.weatherapp.util.Constants.SNOW_BACKGROUND_NIGHT
import com.ozolsugis.weatherapp.util.Constants.THUNDERSTORM_BACKGROUND_DAY
import com.ozolsugis.weatherapp.util.Constants.THUNDERSTORM_BACKGROUND_NIGHT
import java.time.Instant
import java.time.format.DateTimeFormatter

object WeatherInfoFunctions {
    fun isNight(iconId: String) : Boolean{
        return iconId.contains("n")
    }
    fun getIcon(iconId: String) : Int{
        var icon = 0
        when(iconId) {
            in Constants.BROKEN_CLOUDS -> {
                icon = Constants.BROKEN_CLOUDS_ICON

            }
            in Constants.CLEAR_SKY_DAY -> {
                icon = Constants.CLEAR_SKY_DAY_ICON

            }
            in Constants.CLEAR_SKY_NIGHT -> {
                icon = Constants.CLEAR_SKY_NIGHT_ICON

            }
            in Constants.FEW_CLOUDS_DAY -> {
                icon = Constants.FEW_CLOUDS_DAY_ICON

            }
            in Constants.CLOUDS_DAY -> {
                icon = Constants.CLOUDS_DAY_ICON

            }
            in Constants.FEW_CLOUDS_NIGHT -> {
                icon = Constants.FEW_CLOUDS_NIGHT_ICON

            }
            in Constants.MIST -> {
                icon = Constants.MIST_ICON

            }
            in Constants.RAIN_DAY -> {
                icon = Constants.RAIN_DAY_ICON

            }
            in Constants.RAIN_FULL_OF_CLOUDS -> {
                icon = Constants.RAIN_FULL_OF_CLOUDS_ICON

            }
            in Constants.RAIN_NIGHT -> {
                icon = Constants.RAIN_NIGHT_ICON

            }
            in Constants.SNOW -> {
                icon = Constants.SNOW_ICON

            }
            in Constants.THUNDERSTORM -> {
                icon = Constants.THUNDERSTORM_ICON

            }
        }
        return icon
    }
    fun checkForWeatherId(weatherDataId: Int, iconId: String): List<Color>{
        var listOfColors : List<Color> = listOf()
        when(weatherDataId){
            in Constants.THUNDERSTORM_LIST ->{
                listOfColors = if(isNight(iconId)){
                    THUNDERSTORM_BACKGROUND_NIGHT
                }else{
                    THUNDERSTORM_BACKGROUND_DAY
                }
            }
            in Constants.RAIN_LIST ->{
                listOfColors = if(isNight(iconId)){
                    RAIN_BACKGROUND_NIGHT
                }else{
                    RAIN_BACKGROUND_DAY
                }

            }
            in Constants.SNOW_LIST ->{
                listOfColors = if(isNight(iconId)){
                    SNOW_BACKGROUND_NIGHT
                }else{
                    SNOW_BACKGROUND_DAY
                }
            }
            in Constants.CLEARSKY_LIST ->{
                listOfColors = if(isNight(iconId)){
                    CLEAR_BACKGROUND_NIGHT

                }else{
                    CLEAR_BACKGROUND_DAY
                }
            }
            in Constants.CLOUDS_LIST ->{
                listOfColors = if(isNight(iconId)){
                    CLOUDS_BACKGROUND_NIGHT

                }else{
                    CLOUDS_BACKGROUND_DAY

                }

            }
        }
        return listOfColors
    }
    fun getWindDirection(windDir: Int):String{
        var windDirection = ""
        when(windDir){
            in 0..20 ->{
                windDirection = "E"
            }
            in 20..70 ->{
                windDirection = "NE"
            }
            in 70..110 ->{
                windDirection = "N"
            }
            in 110..160 ->{
                windDirection = "NW"
            }
            in 160..200 ->{
                windDirection = "W"
            }
            in 200..250 ->{
                windDirection = "SW"
            }
            in 250..290 ->{
                windDirection = "S"
            }
            in 290..340 ->{
                windDirection = "SE"
            }
            in 340..360 ->{
                windDirection = "E"
            }

        }
        return windDirection
    }
}

fun timeFormat(time: Long): String {
    return DateTimeFormatter.ISO_INSTANT
        .format(Instant.ofEpochSecond(time))
}