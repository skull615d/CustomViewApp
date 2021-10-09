package me.igorfedorov.myapp.feature.settings_dialog.data.api

import me.igorfedorov.myapp.feature.settings_dialog.data.api.model.CitiesModel
import retrofit2.http.GET
import retrofit2.http.Query

interface CitiesApi {

    @GET("/v1/geo/cities")
    suspend fun getCities(
        @Query("namePrefix") cityName: String
    ): CitiesModel

}