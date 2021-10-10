package me.igorfedorov.myapp.feature.weather_screen.data.api

import me.igorfedorov.myapp.feature.weather_screen.data.api.model.WeatherMainModel

class WeatherRepositoryImpl(
    private val weatherRemoteSource: WeatherRemoteSource
) : WeatherRepository {
    override suspend fun getWeatherByCity(cityName: String): WeatherMainModel {
        return weatherRemoteSource.getWeatherByCity(cityName)
    }
}