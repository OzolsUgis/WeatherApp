package com.ozolsugis.weatherapp.presentation.homepage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ozolsugis.weatherapp.data.remote.responses.WeatherForecast
import com.ozolsugis.weatherapp.util.Resource


@Composable
fun WeatherAppHomePage(
    latitude: Double,
    longitude: Double,
    viewModel: WeatherAppHomePageViewModel = hiltViewModel()
) {
    val weatherData = produceState<Resource<WeatherForecast>>(initialValue = Resource.Loading()){
        value = viewModel.getWeatherForecast(latitude,longitude)
    }.value
    
    WeatherState(weatherData = weatherData)


    if (weatherData is Resource.Success){
        weatherData.data?.let{location ->
            Column(modifier = Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
                Text(text = "Nearest city = ${location.name}", color = Color.White, fontSize = 30.sp)
                Text(text = "Temperature = ${location.main.temp}", color = Color.White, fontSize = 30.sp)
                Text(text = "Feels like  = ${location.main.feels_like}", color = Color.White, fontSize = 30.sp)
                Text(text = "Wind Speed  = ${location.wind.speed}", color = Color.White, fontSize = 30.sp)
                Text(text = location.weather[0].description, color = Color.White, fontSize = 30.sp)


            }




        }
    }


}



@Composable
fun WeatherState(
    weatherData: Resource<WeatherForecast>,
    modifier: Modifier = Modifier,
) {
    when(weatherData){
        is Resource.Success ->{

        }
        is Resource.Error -> {
            Text(
                text = weatherData.message!!,
                color = Color.Red,
                modifier = modifier
            )
        }
        is Resource.Loading -> {
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .size(100.dp)
                    .fillMaxSize()
                    .padding(16.dp)
            )
        }
    }

}