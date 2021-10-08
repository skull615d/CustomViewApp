package me.igorfedorov.myapp.feature.weather_screen.data.api.model

import com.google.gson.annotations.SerializedName
import me.igorfedorov.myapp.feature.weather_screen.domain.model.WeatherMain

data class WeatherMainModel(
    @SerializedName("coord")
    val coordinatesModel: CoordinatesModel,
    @SerializedName("weather")
    val weatherModel: List<WeatherModel>,
    @SerializedName("base")
    val base: String,
    @SerializedName("main")
    val mainModel: MainModel,
    @SerializedName("name")
    val name: String
)

fun WeatherMainModel.toWeatherMain() =
    WeatherMain(
        coordinates = coordinatesModel.toCoordinates(),
        weather = weatherModel.map { it.toWeather() },
        base = base,
        main = mainModel.toMain(),
        name = name
    )
