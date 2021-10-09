package me.igorfedorov.myapp.feature.settings_dialog.data.api

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.igorfedorov.myapp.common.Resource
import me.igorfedorov.myapp.feature.settings_dialog.data.api.model.toCitiesData
import me.igorfedorov.myapp.feature.settings_dialog.domain.model.CitiesData
import retrofit2.HttpException
import java.io.IOException

class CitiesRemoteSource(private val api: CitiesApi) {

    fun getCitiesData(cityName: String): Flow<Resource<List<CitiesData>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val cities = api.getCities(cityName).citiesDataModel.map { it.toCitiesData() }
                emit(Resource.Success(data = cities))
            } catch (e: HttpException) {
                emit(Resource.Error(message = e.localizedMessage ?: "An unexpected error occurred"))
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        message = e.localizedMessage
                            ?: "Couldn't reach server. Check your internet connection"
                    )
                )
            }
        }
    }
}