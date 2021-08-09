package com.ozolsugis.weatherapp.presentation.homepage


import androidx.compose.foundation.Image
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect

import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.painter.Painter

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

import com.ozolsugis.weatherapp.data.remote.responses.WeatherForecast
import com.ozolsugis.weatherapp.presentation.ui.theme.*
import com.ozolsugis.weatherapp.presentation.ui.theme.NightBlue
import com.ozolsugis.weatherapp.util.Constants
import com.ozolsugis.weatherapp.util.Constants.APPLICATION_NAME

import com.ozolsugis.weatherapp.util.Constants.COMPASS
import com.ozolsugis.weatherapp.util.Constants.CURRENT_TIME

import com.ozolsugis.weatherapp.util.Constants.SUNRISE
import com.ozolsugis.weatherapp.util.Constants.SUNSET

import com.ozolsugis.weatherapp.util.Constants.WIND_SPEED
import com.ozolsugis.weatherapp.util.Resource
import com.ozolsugis.weatherapp.util.WeatherInfoFunctions.checkForWeatherId
import com.ozolsugis.weatherapp.util.WeatherInfoFunctions.getIcon
import com.ozolsugis.weatherapp.util.WeatherInfoFunctions.getWindDirection
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

import kotlin.math.roundToInt
import com.ozolsugis.weatherapp.util.timeFormat as timeFormat
import kotlin.Int as Int


@Composable
fun WeatherAppHomePage(
    latitude: Double,
    longitude: Double,
    viewModel: WeatherAppHomePageViewModel = hiltViewModel()
) {
    val weatherData = produceState<Resource<WeatherForecast>>(initialValue = Resource.Loading()) {
        value = viewModel.getWeatherForecast(latitude, longitude)
    }.value
    WeatherState(weatherData = weatherData, Modifier.fillMaxSize())


}

@Composable
fun MainScreen(
    city: String,
    iconId: String,
    weatherDataId: Int,
    description: String,
    temp: Double,
    minTemp: Double,
    maxTemp: Double,
    windSpeed: Double,
    windDir: Int,
    sunriseTime: Long,
    sunsetTime: Long
) {
    val backgroundColors = checkForWeatherId(weatherDataId, iconId)
    val systemUiController = rememberSystemUiController()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    backgroundColors,
                    tileMode = TileMode.Clamp
                )
            ),
        Arrangement.Top,
        Alignment.CenterHorizontally
    ) {
        SideEffect() {
            systemUiController.setSystemBarsColor(backgroundColors[0])
        }
        CurrentLocationAndDateSection(
            city = city,
            time = CURRENT_TIME
        )
        WeatherIconAndStateSection(
            weatherDescription = description,
            iconId = iconId
        )
        TemperatureSection(
            currentTemperature = temp,
            minTemperature = minTemp,
            maxTemperature = maxTemp
        )
        WeatherConditionSection(
            windSpeed = windSpeed,
            sunsetTime = sunsetTime,
            sunriseTime = sunriseTime,
            windDir = windDir
        )
    }
}


@Composable
fun CurrentLocationAndDateSection(
    city: String,
    time: String
) {
    Text(
        text = city,
        fontSize = 40.sp,
        modifier = Modifier.padding(top = 16.dp)
    )
    Text(text = time)

    Spacer(modifier = Modifier.size(150.dp))
}

@Composable
fun WeatherIconAndStateSection(
    weatherDescription: String,
    iconId: String
) {
    val icon = getIcon(iconId)
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 50.dp)

    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(75.dp)
        )
        Text(
            text = "${weatherDescription[0].uppercaseChar()}${weatherDescription.substring(1)}",
            fontWeight = FontWeight.Normal,
            fontSize = 26.sp,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )
    }
}

@Composable
fun TemperatureSection(
    currentTemperature: Double,
    minTemperature: Double,
    maxTemperature: Double
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 50.dp, end = 30.dp, bottom = 16.dp)

    ) {
        Text(
            text = "${currentTemperature.roundToInt()}°",
            fontSize = 100.sp,
            fontWeight = FontWeight.Thin,

            )
        Column() {
            Text(
                text = "${minTemperature.roundToInt()}° C",
                fontSize = 30.sp,
                fontWeight = FontWeight.Thin,
            )
            Text(
                text = "${maxTemperature.roundToInt()}° C",
                fontSize = 30.sp,
                fontWeight = FontWeight.Thin,
            )
        }
    }
    Spacer(modifier = Modifier.size(70.dp))
}

@Composable
fun WeatherIconAndValue(
    iconPainter: Painter,
    value: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = iconPainter,
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(text = value, fontWeight = FontWeight.Thin, fontSize = 20.sp)
    }
}

@Composable
fun WeatherConditionSection(
    windSpeed: Double,
    sunsetTime: Long,
    sunriseTime: Long,
    windDir: Int
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 50.dp, end = 30.dp)
    ) {
        val compass = painterResource(id = COMPASS)
        val windSpeedIcon = painterResource(id = WIND_SPEED)
        val sunset = painterResource(id = SUNSET)
        val sunrise = painterResource(id = SUNRISE)


        val formattedSunriseTime = timeFormat(sunriseTime).substring(11, 16)
        val formattedSunsetTime = timeFormat(sunsetTime).substring(11, 16)

        val windDirection = getWindDirection(windDir)


        WeatherIconAndValue(windSpeedIcon, "$windSpeed m/s")
        WeatherIconAndValue(compass, windDirection)
        WeatherIconAndValue(sunrise, formattedSunriseTime)
        WeatherIconAndValue(sunset, formattedSunsetTime)
    }
}


@Composable
fun WeatherState(
    weatherData: Resource<WeatherForecast>,
    modifier: Modifier = Modifier,
) {
    when (weatherData) {
        is Resource.Success -> {
            weatherData.data?.let { location ->
                MainScreen(
                    city = location.name,
                    iconId = location.weather[0].icon,
                    weatherDataId = location.weather[0].id,
                    description = location.weather[0].main,
                    temp = location.main.temp,
                    minTemp = location.main.temp_min,
                    maxTemp = location.main.temp_max,
                    windSpeed = location.wind.speed,
                    windDir = location.wind.deg,
                    sunriseTime = location.sys.sunrise,
                    sunsetTime = location.sys.sunset
                )
            }
        }
        is Resource.Error -> {
            Text(
                text = weatherData.message!!,
                color = Color.Red,
                modifier = modifier
            )
        }
        is Resource.Loading -> {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding()
                    .background(
                        Brush.verticalGradient(Constants.CLOUDS_BACKGROUND_DAY)
                    ),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = APPLICATION_NAME, fontSize = 40.sp, fontWeight = FontWeight.Normal)
                CircularProgressIndicator(
                    color = Color.Black,
                    strokeWidth = 5.dp,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(bottom = 100.dp)
                        .size(150.dp)

                )
            }


        }
    }

}