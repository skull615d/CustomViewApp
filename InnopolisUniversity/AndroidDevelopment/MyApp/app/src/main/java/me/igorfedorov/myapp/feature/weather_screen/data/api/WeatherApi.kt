package me.igorfedorov.myapp.feature.weather_screen.data.api

import me.igorfedorov.myapp.feature.weather_screen.data.api.model.WeatherMainModel
import retrofit2.http.GET
import retrofit2.http.Query


// api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}

interface WeatherApi {

    @GET("/data/2.5//weather")
    suspend fun getWeatherByCityName(
        @Query("q") cityName: String,
        @Query("units") units: String = "metric"
    ): WeatherMainModel


}