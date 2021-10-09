package me.igorfedorov.myapp.feature.weather_screen.data.api

import kotlinx.coroutines.flow.Flow
import me.igorfedorov.myapp.common.Resource
import me.igorfedorov.myapp.feature.weather_screen.domain.model.WeatherMain

class WeatherRepositoryImpl(
    private val weatherRemoteSource: WeatherRemoteSource
) : WeatherRepository {
    override fun getWeatherByCity(cityName: String): Flow<Resource<WeatherMain>> {
        return weatherRemoteSource.getWeatherByCity(cityName)
    }
}