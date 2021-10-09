package me.igorfedorov.myapp.feature.settings_dialog.data.api

import kotlinx.coroutines.flow.Flow
import me.igorfedorov.myapp.common.Resource
import me.igorfedorov.myapp.feature.settings_dialog.domain.model.CitiesData

class CitiesRepositoryImpl(
    private val citiesRemoteSource: CitiesRemoteSource
) : CitiesRepository {
    override fun getCitiesData(cityName: String): Flow<Resource<List<CitiesData>>> {
        return citiesRemoteSource.getCitiesData(cityName)
    }
}