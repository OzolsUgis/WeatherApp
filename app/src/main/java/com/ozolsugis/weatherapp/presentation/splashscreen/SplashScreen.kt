package com.ozolsugis.weatherapp.presentation.splashscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ozolsugis.weatherapp.util.Constants

@Composable
fun SplashScreen(
) {
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
        Text(text = Constants.APPLICATION_NAME,fontSize = 40.sp, fontWeight = FontWeight.Normal)
        CircularProgressIndicator(
            color = Color.Black,
            strokeWidth = 5.dp,
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp, bottom = 100.dp)
                .size(150.dp)
        )
    }

}