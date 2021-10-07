package me.igorfedorov.myapp.feature.weather_screen.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherFromApi(
    @Json(name = "coord")
    val coordinates: Coordinates,
    @Json(name = "weather")
    val weather: List<Weather>,
    @Json(name = "base")
    val base: String,
    @Json(name = "main")
    val main: Main
)
