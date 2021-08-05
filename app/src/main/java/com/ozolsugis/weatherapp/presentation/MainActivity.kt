package com.ozolsugis.weatherapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ozolsugis.weatherapp.presentation.homepage.WeatherAppHomePage
import com.ozolsugis.weatherapp.presentation.splashscreen.SplashScreen
import com.ozolsugis.weatherapp.presentation.ui.theme.CloudsDayLight
import com.ozolsugis.weatherapp.presentation.ui.theme.CloudsDayMedium
import com.ozolsugis.weatherapp.presentation.ui.theme.CloudsNightLight
import com.ozolsugis.weatherapp.presentation.ui.theme.WeatherAppTheme
import com.ozolsugis.weatherapp.services.LocationService
import com.ozolsugis.weatherapp.util.Constants.CLOUDS_BACKGROUND_DAY
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        val locationUpdate = LocationService(this,this)
        locationUpdate.getLocationUpdate()




        CoroutineScope(Dispatchers.Main).launch {
            setContent {
                val systemUiController = rememberSystemUiController()
                SideEffect() {
                    systemUiController.setSystemBarsColor(CloudsDayMedium)
                }
              SplashScreen()
            }
            delay(2000L)
            val latitude: Double = locationUpdate.latitude
            val longitude: Double = locationUpdate.longitude

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

