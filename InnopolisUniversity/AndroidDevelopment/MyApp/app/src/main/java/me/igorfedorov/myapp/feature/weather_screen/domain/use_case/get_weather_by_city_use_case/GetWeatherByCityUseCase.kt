package me.igorfedorov.myapp.feature.weather_screen.domain.use_case.get_weather_by_city_use_case

import me.igorfedorov.myapp.feature.weather_screen.data.api.WeatherRepository

class GetWeatherByCityUseCase(
    private val repository: WeatherRepository
) {

    suspend operator fun invoke(cityName: String) = repository.getWeatherByCity(cityName)

}