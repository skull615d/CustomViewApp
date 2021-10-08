package me.igorfedorov.myapp.feature.weather_screen.data.api.model

import com.google.gson.annotations.SerializedName
import me.igorfedorov.myapp.feature.weather_screen.domain.model.Weather

data class WeatherModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String
)

fun WeatherModel.toWeather() =
    Weather(
        id = id,
        main = main,
        description = description
    )