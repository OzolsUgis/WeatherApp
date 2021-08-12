<h1 align="center">Weather App</h1>
<p align="center"> Current location weather information</p>

<details open ="open">
  <summary>Contains</summary>
  <ol>
    <li>
      <a href='#about-the-project'>About Project</a>
        <ul>
          <li><a href="#built-with">Built With</a></li>
        </ul>
    </li>
    <li>
      <a href='#getting-started'>Getting Started</a>
        <ul>
          <li><a href="#prerequisites">Prerequisites</a></li>
        </ul> 
        <ul>
          <li><a href="#installation">Installation</a></li>
        </ul>
    </li> 
    <li>
      <a href='#usage'>Usage</a>
         <ul>
          <li><a href="#changing-language-and-units">Changing language and units</a></li>
        </ul> 
       <ul>
          <li><a href="#specify-your-own-background-colors">Specyfy your own wather background colors</a></li>
        </ul> 
        <ul>
          <li><a href="#add-new-data-blocks">Add new data blocks</a></li>
        </ul>
    </li>
    <li>
      <a href='#contacts'>Contacts</a> 
    </li>
  </ol>
</details>
  
## About Project
![product-screenshot](https://imagizer.imageshack.com/v2/1280x1024q90/924/M6UCGJ.jpg)

I introduce you to Weather App, This application is ment for thoes who wants to follow everydays weather conditions. This application allows you to find out your current locations
weather. It takes your current location via GPS or Network, sends data to weather api and returns weather details, and then they are displayed in a viewers friendly way. This application is in Alpha version so it means its still in developing state, but it serves its main purpose. 

This app :
*  Have a single read-only page, so its easy to use
*  Background according to current weather (It's individualy themed for every state of weather) 
*  It shows the nearest place where api can get info from, so you can see for example nearest city
*  Main weather information ( highest , lowest temperature, wind speed e.t.c)

### Built With 

This application is built in  [Android Studio version 2020.3.1 (Artic Fox)](https://developer.android.com/studio?gclid=CjwKCAjwgb6IBhAREiwAgMYKRlU8WsxaTu6kg3JANeH6rEr8MrWyit5JaDfcTy0v1tTP0-DOmL1QnRoCxrcQAvD_BwE&gclsrc=aw.ds) 
using :

* [Kotlin](https://developer.android.com/kotlin)
* [Jetpack Compose version 1.0.0](https://developer.android.com/jetpack/compose?gclid=EAIaIQobChMImIyxhI-i8gIVlgCiAx3kZgYlEAAYASAAEgL1J_D_BwE&gclsrc=aw.ds)
* [Courotines](https://developer.android.com/kotlin/coroutines?gclid=EAIaIQobChMIqZC4jo-i8gIVsAZ7Ch1rOASzEAAYASAAEgKAwvD_BwE&gclsrc=aw.ds)
* [DaggerHilt](https://developer.android.com/training/dependency-injection/hilt-android)
* [Retrofit & OkHttp](https://square.github.io/retrofit/)
* [GoogleMap services (location)](https://developers.google.com/android/reference/com/google/android/gms/location/package-summary)
* [Easy Permissions](https://github.com/googlesamples/easypermissions)
* [Timber](https://github.com/JakeWharton/timber)
* [Accompanist](https://github.com/google/accompanist)
* [Open Weather Map](https://openweathermap.org/)

## Getting Started
### Prerequisites 

First of all you need to install Android Studio version 2020.3.1 (Artic Fox) or newer version who supports Jetpack compose
you can find installation step-by-step in following : 

* [Android Studio version 2020.3.1 (Artic Fox)](https://developer.android.com/studio?gclid=CjwKCAjwgb6IBhAREiwAgMYKRlU8WsxaTu6kg3JANeH6rEr8MrWyit5JaDfcTy0v1tTP0-DOmL1QnRoCxrcQAvD_BwE&gclsrc=aw.ds) 

### Installation 

1. You will need your own API Key, you can get it for free at https://home.openweathermap.org/
2. Clone the repository using : 

* Android studio **file -> New -> Project from version control...** And enter this https://github.com/OzolsUgis/WeatherApp.git in URL

* Using terminal 
  ```sh
   git clone https://github.com/OzolsUgis/WeatherApp.git
   ```
   
3. Create new **gradle.properties** file in App folder


    ![product-screenshot](https://imagizer.imageshack.com/v2/640x480q90/924/RZxeZA.png)
    ![product-screenshot](https://imagizer.imageshack.com/v2/320x240q90/922/gvwzIy.png)
    
4. Add these 3 atributes to avoid any gradle errors
    ```kotlin
      
    org.gradle.jvmargs=-Xmx2048m -Dfile.encoding=UTF-8
    android.useAndroidX=true
    android.enableJetifier=true
    ```
6. ADD your API KEY
    ```kotlin
      API_KEY = 'ENTER YOUR API KEY HERE'
    ```
7. **Sync** project and its ready to use 
    
## Usage

This project is built in Jetpack Compose which makes UI more developer friendly you can reuse UI composables without making new ones
In this project you can show data fields in your own way. 

### Changing language and units 
* This example will show you how to change language and data units
1. in **weatherapp/data/remote/WeatherApi.kt** language row you specify new default value with country code you like (You can find country codes in API documentation)
   ```kotlin
    @Query("lang") language : String = "en",
    ```
2. In unit row you specify new default units you like (You can find units in API documentation)
   ```kotlin
    @Query("units") units : String = "metric",
    ```


### Specify your own background colors
* Specify your own background color list. I will show you example how to change color background for Cloudy weather in day. 
1. In **weatherapp/presentaiton/ui/Color.kt** secify new colors.
    ```kotlin
    val CloudsDayLight = Color(0xFFE7E3FF)
    val CloudsDayMedium = Color(0xFFC7C3DF)
    ```
        
2. Make a list of thoes colors in  **weatherapp/util/Constants.kt**
    ```kotlin
    val CLOUDS_BACKGROUND_DAY = listOf(CloudsDayMedium, CloudsDayLight)
    ```
3. In **weatherapp/util/WeatherInfoFunctions.kt**  checkForWeatherId function specify return to created list
     ```kotlin
          in Constants.CLOUDS_LIST ->{
                // Warning this if condition checks if api response returns
                // that its night time, so for day you need to specify list in else block
                listOfColors = if(isNight(iconId)){
                    CLOUDS_BACKGROUND_NIGHT

                }else{
                    CLOUDS_BACKGROUND_DAY

                }

            }
    ```
### Add new data blocks
* Add new data blocks in this example i will show how to add new data blocks to UI 
1. You need to add parameter to composable function. You can do that in **weatherapp/presentation/homepage/WeatherAppHomePage.kt**
MainScreen composable 
   ```kotlin
    fun MainScreen(
        city: String,
        iconId: String,
        weatherDataId: Int,
        description: String,
        temp: Double,
        windSpeed: Double,
        windDir: Int,
        sunriseTime: Long,
        sunsetTime: Long,
        // Add new paremeters to function to call later, in this example i will implement min and max temp
        minTemp: Double,
        maxTemp: Double
      )
    ```
2. If we specify new  parameters you need to pass them in WeatherState composable function in **Resource.Success** state
    * You can find correct field information in https://openweathermap.org/current Fields in API response, for example if i want to access min and max temperature
    i must specify my parameter as **main.temp_min** & **main.temp_max**
    ```kotlin
       is Resource.Success -> {
            // Notice that here i null check for data 
            weatherData.data?.let { location ->
                MainScreen(
                    city = location.name,
                    iconId = location.weather[0].icon,
                    weatherDataId = location.weather[0].id,
                    description = location.weather[0].main,
                    temp = location.main.temp,
                    windSpeed = location.wind.speed,
                    windDir = location.wind.deg,
                    sunriseTime = location.sys.sunrise,
                    sunsetTime = location.sys.sunset,
                    // Here i will add my new parameters 
                    minTemp = location.main.temp_min,
                    maxTemp = location.main.temp_max
                )
            }
        }
    ```
3. Now i can build my UI in TemperatureSection function 
    * I want to put min and max temperatures next to my current temperature so i use Row composable and in Row Scope 
    i make Column composable because i want max temperature be on top of min temperature 
    ```kotlin
           Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.SpaceBetween,
              modifier = Modifier
                  .fillMaxWidth()
                  .wrapContentHeight()
                  .padding(start = 50.dp, end = 30.dp, bottom = 16.dp)

           ) {
            //  Here starts Row scope
                Text(
                    text = "${temp.roundToInt()}°",
                    fontSize = 100.sp,
                    fontWeight = FontWeight.Thin,
                    )
                Column() {
                    // Here starts column scope, and here i make simple Text composable and in text parameter i
                    // insert my max & min temperature  parameters and because they are Double data type i round them to Int 
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
    ```
## Contacts

Ugis Ozols - (This will follow)

Project Link - https://github.com/OzolsUgis/WeatherApp






