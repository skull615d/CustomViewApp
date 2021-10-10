package me.igorfedorov.myapp.feature.weather_screen.data.api.model

import me.igorfedorov.myapp.feature.weather_screen.domain.model.Coordinates
import me.igorfedorov.myapp.feature.weather_screen.domain.model.Main
import me.igorfedorov.myapp.feature.weather_screen.domain.model.Weather
import me.igorfedorov.myapp.feature.weather_screen.domain.model.WeatherMain

fun CoordinatesModel.toCoordinates() = Coordinates(
    lon = lon,
    lat = lat
)

fun MainModel.toMain() = Main(
    temp = temp,
    pressure = pressure,
    humidity = humidity,
    tempMax = tempMax,
    tempMin = tempMin
)


fun WeatherModel.toWeather() = Weather(
    id = id,
    main = main,
    description = description
)

fun WeatherMainModel.toWeatherMain() = WeatherMain(
    coordinates = coordinatesModel.toCoordinates(),
    weather = weatherModel.map { it.toWeather() },
    base = base,
    main = mainModel.toMain(),
    name = name
)