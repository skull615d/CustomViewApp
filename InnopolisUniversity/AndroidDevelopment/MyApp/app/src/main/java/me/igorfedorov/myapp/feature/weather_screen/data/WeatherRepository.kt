package me.igorfedorov.myapp.feature.weather_screen.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.igorfedorov.myapp.common.Resource
import me.igorfedorov.myapp.feature.weather_screen.data.api.WeatherApi
import me.igorfedorov.myapp.feature.weather_screen.data.model.WeatherFromApi
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

interface WeatherRepository {

    fun getWeatherByCityName(cityName: String): Flow<Resource<WeatherFromApi>>

}

class WeatherRepositoryImpl(
    private val api: WeatherApi
) : WeatherRepository {
    override fun getWeatherByCityName(cityName: String): Flow<Resource<WeatherFromApi>> {
        Timber.d("getWeatherByCityName Called in Repo")
        return flow {
            try {
                emit(Resource.Loading())
                val weather = api.getWeatherByCityName(cityName)
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