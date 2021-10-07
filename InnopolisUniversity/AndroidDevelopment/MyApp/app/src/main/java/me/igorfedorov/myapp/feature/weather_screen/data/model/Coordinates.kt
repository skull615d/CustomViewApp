package me.igorfedorov.myapp.feature.weather_screen.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Json(name = "coord")
data class Coordinates(
    @Json(name = "lon")
    val lon: Double,
    @Json(name = "lat")
    val lat: Double
)