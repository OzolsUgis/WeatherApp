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

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

import com.ozolsugis.weatherapp.data.remote.responses.WeatherForecast
import com.ozolsugis.weatherapp.presentation.ui.theme.*
import com.ozolsugis.weatherapp.presentation.ui.theme.NightBlue
import com.ozolsugis.weatherapp.util.Constants.APPLICATION_NAME

import com.ozolsugis.weatherapp.util.Constants.COMPASS

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
    val icon = getIcon(iconId)
    val systemUiController = rememberSystemUiController()
    val forecastTimeFormat = DateFormat.getDateTimeInstance(
        DateFormat.DEFAULT,
        DateFormat.SHORT
    ).format(Date())

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
        Text(
            text = city,
            fontSize = 40.sp,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(text = forecastTimeFormat)

        Spacer(modifier = Modifier.size(150.dp))
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 50.dp)

        ) {
            val image = painterResource(id = icon)
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier.size(75.dp)
            )
            Text(
                text = "${description[0].uppercaseChar()}${description.substring(1)}",
                fontWeight = FontWeight.Normal,
                fontSize = 26.sp,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 50.dp, end = 30.dp, bottom = 16.dp)

        ) {
            Text(
                text = "${temp.roundToInt()}°",
                fontSize = 100.sp,
                fontWeight = FontWeight.Thin,

                )
            Column() {
                Text(
                    text = "${maxTemp.roundToInt()}° C",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Thin,
                )
                Text(
                    text = "${minTemp.roundToInt()}° C",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Thin,
                )
            }
        }
        Spacer(modifier = Modifier.size(70.dp))
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
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = windSpeedIcon,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text(text = "$windSpeed m/s", fontWeight = FontWeight.Thin, fontSize = 20.sp)
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Image(
                    painter = compass,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text(text = windDirection, fontWeight = FontWeight.Thin, fontSize = 20.sp)
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Image(
                    painter = sunrise,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text(text = formattedSunriseTime, fontWeight = FontWeight.Thin, fontSize = 20.sp)
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = sunset,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text(text = formattedSunsetTime, fontWeight = FontWeight.Thin, fontSize = 20.sp)
            }
        }


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
                    .background(
                        Brush.verticalGradient(
                            listOf(LightOrange, NightBlue),
                            tileMode = TileMode.Mirror
                        )
                    ),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = APPLICATION_NAME,
                    color = TextColor,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                )
                CircularProgressIndicator(
                    color = Color.Yellow,
                    modifier = Modifier
                        .size(150.dp)
                        .fillMaxSize()
                        .padding(16.dp),
                    strokeWidth = 5.dp,
                )
            }


        }
    }

}