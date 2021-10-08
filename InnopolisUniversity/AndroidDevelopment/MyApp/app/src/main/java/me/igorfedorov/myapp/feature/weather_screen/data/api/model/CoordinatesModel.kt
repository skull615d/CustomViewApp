package me.igorfedorov.myapp.feature.weather_screen.data.api.model

import com.google.gson.annotations.SerializedName
import me.igorfedorov.myapp.feature.weather_screen.domain.model.Coordinates

data class CoordinatesModel(
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("lat")
    val lat: Double
)

fun CoordinatesModel.toCoordinates(): Coordinates {
    return Coordinates(
        lon = lon,
        lat = lat
    )
}