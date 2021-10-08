package me.igorfedorov.myapp.feature.weather_screen.data.api

import kotlinx.coroutines.flow.Flow
import me.igorfedorov.myapp.common.Resource
import me.igorfedorov.myapp.feature.weather_screen.domain.model.WeatherMain

interface WeatherRepository {

    fun getWeatherByCityName(cityName: String): Flow<Resource<WeatherMain>>

}

