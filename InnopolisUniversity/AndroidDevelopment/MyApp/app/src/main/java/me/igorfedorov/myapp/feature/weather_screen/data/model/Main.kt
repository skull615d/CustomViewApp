package me.igorfedorov.myapp.feature.weather_screen.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Main(
    @Json(name = "temp")
    val temp: Double,
    @Json(name = "pressure")
    val pressure: Int,
    @Json(name = "humidity")
    val humidity: Int,
    @Json(name = "temp_min")
    val temp_min: Double,
    @Json(name = "temp_max")
    val temp_max: Double
)