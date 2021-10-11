package me.igorfedorov.myapp.feature.settings_screen.data.api

import me.igorfedorov.myapp.feature.settings_screen.data.api.model.CityDataModel

interface CitiesRepository {

    suspend fun getCitiesData(cityName: String): List<CityDataModel>

}