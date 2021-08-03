package com.ozolsugis.weatherapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ozolsugis.weatherapp.presentation.homepage.WeatherAppHomePage
import com.ozolsugis.weatherapp.presentation.ui.theme.WeatherAppTheme
import com.ozolsugis.weatherapp.services.LocationService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val locationUpdate = LocationService(this,this)
        locationUpdate.getLocationUpdate()



        CoroutineScope(Dispatchers.Main).launch {
            delay(2000L)
            var latitude: Double = locationUpdate.latitude
            var longitude: Double = locationUpdate.longitude

            setContent {
                WeatherAppTheme {
                    WeatherAppHomePage(
                        latitude = latitude,
                        longitude = longitude
                    )
                }
            }
        }

    }
}

