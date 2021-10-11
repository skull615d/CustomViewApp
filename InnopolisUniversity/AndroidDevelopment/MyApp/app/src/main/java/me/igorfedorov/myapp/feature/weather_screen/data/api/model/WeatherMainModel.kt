package me.igorfedorov.myapp.feature.weather_screen.data.api.model

import com.google.gson.annotations.SerializedName

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