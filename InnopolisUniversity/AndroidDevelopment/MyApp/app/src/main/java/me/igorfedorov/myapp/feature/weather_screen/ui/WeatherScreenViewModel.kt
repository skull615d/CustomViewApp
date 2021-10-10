package me.igorfedorov.myapp.feature.weather_screen.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import me.igorfedorov.myapp.common.Resource
import me.igorfedorov.myapp.feature.weather_screen.data.api.model.toWeatherMain
import me.igorfedorov.myapp.feature.weather_screen.domain.model.WeatherMain
import me.igorfedorov.myapp.feature.weather_screen.domain.use_case.get_weather_by_city_use_case.GetWeatherByCityUseCase
import retrofit2.HttpException
import java.io.IOException

class WeatherScreenViewModel(
    private val getWeatherByCityUseCase: GetWeatherByCityUseCase
) : ViewModel() {

    private val _weather = MutableStateFlow<Resource<WeatherMain>>(Resource.Initialized())
    val weather: StateFlow<Resource<WeatherMain>>
        get() = _weather


    fun getWeatherByCity(cityName: String) {
        viewModelScope.launch {
            try {
                _weather.emit(Resource.Loading())
                val weather = getWeatherByCityUseCase(cityName).toWeatherMain()
                _weather.emit(Resource.Success(data = weather))
            } catch (e: HttpException) {
                _weather.emit(
                    Resource.Error(
                        message = e.localizedMessage ?: "An unexpected error occurred"
                    )
                )
            } catch (e: IOException) {
                _weather.emit(
                    Resource.Error(
                        message = e.localizedMessage
                            ?: "Couldn't reach server. Check your internet connection"
                    )
                )
            }
        }
    }
}

