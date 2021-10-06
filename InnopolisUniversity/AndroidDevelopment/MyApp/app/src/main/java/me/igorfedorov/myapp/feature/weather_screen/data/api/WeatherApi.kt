package me.igorfedorov.myapp.feature.weather_screen.data.api

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

// 33ae2570581f89ec8a89d91abbcbc508

// api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}

interface WeatherApi {

    @GET("/weather")
    fun getWeather(
        @Query("q") cityName: String,
        @Query("appid") appId: String = "33ae2570581f89ec8a89d91abbcbc508"
    ): ResponseBody


}