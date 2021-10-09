package me.igorfedorov.myapp.feature.settings_dialog.data.api

import kotlinx.coroutines.flow.Flow
import me.igorfedorov.myapp.common.Resource
import me.igorfedorov.myapp.feature.settings_dialog.domain.model.CitiesData

interface CitiesRepository {

    fun getCitiesData(cityName: String): Flow<Resource<List<CitiesData>>>

}