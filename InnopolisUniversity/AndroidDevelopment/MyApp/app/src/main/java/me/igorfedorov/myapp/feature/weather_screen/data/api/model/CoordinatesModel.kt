package me.igorfedorov.myapp.feature.weather_screen.data.api.model

import com.google.gson.annotations.SerializedName

data class CoordinatesModel(
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("lat")
    val lat: Double
)