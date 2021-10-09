package me.igorfedorov.myapp.feature.weather_screen.data.api

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.igorfedorov.myapp.common.Resource
import me.igorfedorov.myapp.feature.weather_screen.data.api.model.toWeatherMain
import me.igorfedorov.myapp.feature.weather_screen.domain.model.WeatherMain
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

class WeatherRemoteSource(private val api: WeatherApi) {

    fun getWeatherByCity(cityName: String): Flow<Resource<WeatherMain>> {
        Timber.d("getWeatherByCityName Called in Repo")
        return flow {
            try {
                emit(Resource.Loading())
                val weather = api.getWeatherByCity(cityName).toWeatherMain()
                emit(Resource.Success(data = weather))
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