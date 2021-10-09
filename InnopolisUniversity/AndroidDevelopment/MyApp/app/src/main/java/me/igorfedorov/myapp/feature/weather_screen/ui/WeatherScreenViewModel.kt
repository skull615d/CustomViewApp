package me.igorfedorov.myapp.feature.weather_screen.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import me.igorfedorov.myapp.common.Resource
import me.igorfedorov.myapp.feature.weather_screen.domain.model.WeatherMain
import me.igorfedorov.myapp.feature.weather_screen.domain.use_case.get_weather_by_city_use_case.GetWeatherByCityUseCase

class WeatherScreenViewModel(
    private val getWeatherByCityUseCase: GetWeatherByCityUseCase
) : ViewModel() {

    private val _weather = MutableStateFlow<Resource<WeatherMain>>(Resource.Initialized())
    val weather: StateFlow<Resource<WeatherMain>>
        get() = _weather


    fun getWeatherByCity(cityName: String) {
        getWeatherByCityUseCase(cityName).onEach {
            _weather.value = it
        }
            .launchIn(viewModelScope)
    }
}

