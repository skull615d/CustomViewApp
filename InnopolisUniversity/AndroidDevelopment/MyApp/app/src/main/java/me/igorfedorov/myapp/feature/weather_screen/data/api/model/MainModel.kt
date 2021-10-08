package me.igorfedorov.myapp.feature.weather_screen.data.api.model

import com.google.gson.annotations.SerializedName
import me.igorfedorov.myapp.feature.weather_screen.domain.model.Main

data class MainModel(
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("temp_min")
    val tempMin: Double,
    @SerializedName("temp_max")
    val tempMax: Double
)

fun MainModel.toMain() =
    Main(
        temp = temp,
        pressure = pressure,
        humidity = humidity,
        tempMax = tempMax,
        tempMin = tempMin
    )