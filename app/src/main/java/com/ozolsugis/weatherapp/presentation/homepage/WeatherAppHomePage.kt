package com.ozolsugis.weatherapp.presentation.homepage

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.transform.CircleCropTransformation
import com.google.accompanist.coil.CoilImage

import com.ozolsugis.weatherapp.R
import com.ozolsugis.weatherapp.data.remote.responses.WeatherForecast
import com.ozolsugis.weatherapp.presentation.ui.theme.LightOrange
import com.ozolsugis.weatherapp.presentation.ui.theme.NightBlue
import com.ozolsugis.weatherapp.presentation.ui.theme.TextColor
import com.ozolsugis.weatherapp.util.Constants.APPLICATION_NAME
import com.ozolsugis.weatherapp.util.Constants.BROKEN_CLOUDS
import com.ozolsugis.weatherapp.util.Constants.BROKEN_CLOUDS_ICON
import com.ozolsugis.weatherapp.util.Constants.CLEARSKY_LIST
import com.ozolsugis.weatherapp.util.Constants.CLEAR_SKY_DAY
import com.ozolsugis.weatherapp.util.Constants.CLEAR_SKY_DAY_ICON
import com.ozolsugis.weatherapp.util.Constants.CLEAR_SKY_NIGHT
import com.ozolsugis.weatherapp.util.Constants.CLEAR_SKY_NIGHT_ICON
import com.ozolsugis.weatherapp.util.Constants.CLOUDS_DAY
import com.ozolsugis.weatherapp.util.Constants.CLOUDS_DAY_ICON
import com.ozolsugis.weatherapp.util.Constants.CLOUDS_LIST
import com.ozolsugis.weatherapp.util.Constants.COMPASS
import com.ozolsugis.weatherapp.util.Constants.FEW_CLOUDS_DAY
import com.ozolsugis.weatherapp.util.Constants.FEW_CLOUDS_DAY_ICON
import com.ozolsugis.weatherapp.util.Constants.FEW_CLOUDS_NIGHT
import com.ozolsugis.weatherapp.util.Constants.FEW_CLOUDS_NIGHT_ICON
import com.ozolsugis.weatherapp.util.Constants.MIST
import com.ozolsugis.weatherapp.util.Constants.MIST_ICON
import com.ozolsugis.weatherapp.util.Constants.RAIN_DAY
import com.ozolsugis.weatherapp.util.Constants.RAIN_DAY_ICON
import com.ozolsugis.weatherapp.util.Constants.RAIN_FULL_OF_CLOUDS
import com.ozolsugis.weatherapp.util.Constants.RAIN_FULL_OF_CLOUDS_ICON
import com.ozolsugis.weatherapp.util.Constants.RAIN_LIST
import com.ozolsugis.weatherapp.util.Constants.RAIN_NIGHT
import com.ozolsugis.weatherapp.util.Constants.RAIN_NIGHT_ICON
import com.ozolsugis.weatherapp.util.Constants.SNOW
import com.ozolsugis.weatherapp.util.Constants.SNOW_ICON
import com.ozolsugis.weatherapp.util.Constants.SNOW_LIST
import com.ozolsugis.weatherapp.util.Constants.SUNRISE
import com.ozolsugis.weatherapp.util.Constants.SUNSET
import com.ozolsugis.weatherapp.util.Constants.THUNDERSTORM
import com.ozolsugis.weatherapp.util.Constants.THUNDERSTORM_ICON
import com.ozolsugis.weatherapp.util.Constants.THUNDERSTORM_LIST
import com.ozolsugis.weatherapp.util.Constants.WIND_SPEED
import com.ozolsugis.weatherapp.util.Resource
import timber.log.Timber
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.roundToInt


@Composable
fun WeatherAppHomePage(
    latitude: Double,
    longitude: Double,
    viewModel: WeatherAppHomePageViewModel = hiltViewModel()
) {
    val weatherData = produceState<Resource<WeatherForecast>>(initialValue = Resource.Loading()){
        value = viewModel.getWeatherForecast(latitude,longitude)
    }.value
    
    WeatherState(weatherData = weatherData, Modifier.fillMaxSize())


}

fun isNight(iconId: String) : Boolean{
    return iconId.contains("n")
}
fun getIcon(iconId: String) : Int{
    var icon : Int = 0
    when(iconId) {
        in BROKEN_CLOUDS -> {
            icon = BROKEN_CLOUDS_ICON

        }
        in CLEAR_SKY_DAY -> {
            icon = CLEAR_SKY_DAY_ICON

        }
        in CLEAR_SKY_NIGHT -> {
            icon = CLEAR_SKY_NIGHT_ICON

        }
        in FEW_CLOUDS_DAY -> {
            icon = FEW_CLOUDS_DAY_ICON

        }
        in CLOUDS_DAY -> {
            icon = CLOUDS_DAY_ICON

        }
        in FEW_CLOUDS_NIGHT -> {
            icon = FEW_CLOUDS_NIGHT_ICON

        }
        in MIST -> {
            icon = MIST_ICON

        }
        in RAIN_DAY -> {
            icon = RAIN_DAY_ICON

        }
        in RAIN_FULL_OF_CLOUDS -> {
            icon = RAIN_FULL_OF_CLOUDS_ICON

        }
        in RAIN_NIGHT -> {
            icon = RAIN_NIGHT_ICON

        }
        in SNOW -> {
            icon = SNOW_ICON

        }
        in THUNDERSTORM -> {
            icon = THUNDERSTORM_ICON

        }
    }
    return icon
}
fun checkForWeatherId(weatherDataId: Int, iconId: String): List<Color>{
    var listOfColors : List<Color> = listOf()
    when(weatherDataId){
        in THUNDERSTORM_LIST ->{
             if(isNight(iconId)){
                 listOfColors =listOf(LightOrange, NightBlue)
                 Timber.d("1")

            }else{
                 listOfColors =listOf(NightBlue, LightOrange)
                 Timber.d("1")
            }

        }
        in RAIN_LIST ->{
            if(isNight(iconId)){
                listOfColors =listOf(LightOrange, NightBlue)
                Timber.d("1")

            }else{
                listOfColors =listOf(NightBlue, LightOrange)
                Timber.d("2")
            }

        }
        in SNOW_LIST ->{
            if(isNight(iconId)){
                listOfColors =listOf(LightOrange, NightBlue)
                Timber.d("3")

            }else{
                listOfColors =listOf(NightBlue, LightOrange)
                Timber.d("4")
            }

        }
        in CLEARSKY_LIST ->{
            if(isNight(iconId)){
                listOfColors =listOf(LightOrange, NightBlue)
                Timber.d("5")

            }else{
                listOfColors =listOf(NightBlue, LightOrange)
                Timber.d("6")
            }

        }
        in CLOUDS_LIST ->{
            if(isNight(iconId)){
                listOfColors =listOf(LightOrange, NightBlue)
                Timber.d("7")

            }else{
                listOfColors =listOf(NightBlue, LightOrange)
                Timber.d("8")
            }

        }
    }
    return listOfColors
}

@Composable
fun MainScreen (
    city : String,
    iconId : String,
    weatherDataId : Int,
    description : String,
    temp : Double,
    minTemp: Double,
    maxTemp: Double,
    windSpeed : Double,
    windDir : Int,
    sunriseTime : Long,
    sunsetTime : Long
) {
    val backgroundColors = checkForWeatherId(weatherDataId, iconId)
    val icon = getIcon(iconId)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    backgroundColors,
                    tileMode = TileMode.Mirror
                )
            ),
        Arrangement.Top,
        Alignment.CenterHorizontally
    ) {
        Text(
            text = city,
            color = TextColor,
            fontSize = 35.sp,
            modifier = Modifier.padding(top = 16.dp)
        )
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
                text = description.uppercase(),
                fontWeight = FontWeight.Thin,
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
        Spacer(modifier = Modifier.size(50.dp))
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 50.dp, end = 30.dp)
            ) {
            val compass  = painterResource(id = COMPASS)
            val windSpeedIcon = painterResource(id = WIND_SPEED)
            val sunset = painterResource(id = SUNSET)
            val sunrise = painterResource(id = SUNRISE)


            val formattedSunriseTime = DateTimeFormatter.ISO_INSTANT
                .format(java.time.Instant.ofEpochSecond(sunriseTime))
            val timeOutput = formattedSunriseTime.dropWhile {it =='T'}
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = windSpeedIcon,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
                Text(text = "$windSpeed m/s", fontWeight = FontWeight.Thin, fontSize = 20.sp)
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Image(
                    painter = compass,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
                Text(text = timeOutput, fontWeight = FontWeight.Thin, fontSize = 20.sp)
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Image(
                    painter = sunrise,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
                Text(text = "$windSpeed m/s", fontWeight = FontWeight.Thin, fontSize = 20.sp)
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = sunset,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
                Text(text = "$windSpeed m/s", fontWeight = FontWeight.Thin, fontSize = 20.sp)
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