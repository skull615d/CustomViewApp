package me.igorfedorov.myapp.feature.weather_screen.data.api

import me.igorfedorov.myapp.feature.weather_screen.data.api.model.WeatherMainModel

interface WeatherRepository {

    suspend fun getWeatherByCity(cityName: String): WeatherMainModel

}

