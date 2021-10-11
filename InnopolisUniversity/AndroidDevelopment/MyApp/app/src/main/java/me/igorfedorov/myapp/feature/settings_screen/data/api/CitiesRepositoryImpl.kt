package me.igorfedorov.myapp.feature.settings_screen.data.api

import me.igorfedorov.myapp.feature.settings_screen.data.api.model.CityDataModel

class CitiesRepositoryImpl(
    private val citiesRemoteSource: CitiesRemoteSource
) : CitiesRepository {
    override suspend fun getCitiesData(cityName: String): List<CityDataModel> {
        return citiesRemoteSource.getCitiesData(cityName)
    }
}