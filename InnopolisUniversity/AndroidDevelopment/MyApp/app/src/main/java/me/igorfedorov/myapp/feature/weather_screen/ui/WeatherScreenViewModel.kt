package me.igorfedorov.myapp.feature.weather_screen.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import me.igorfedorov.myapp.common.Resource
import me.igorfedorov.myapp.feature.weather_screen.data.WeatherRepository
import me.igorfedorov.myapp.feature.weather_screen.data.model.WeatherFromApi

class WeatherScreenViewModel(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _weather = MutableStateFlow<Resource<WeatherFromApi>>(Resource.Initialized())
    val weather: StateFlow<Resource<WeatherFromApi>>
        get() = _weather

    private lateinit var job: Job

    fun getWeatherByCityName(cityName: String) {
        repository.getWeatherByCityName(cityName).onEach {
            _weather.value = it
        }
            .launchIn(viewModelScope)
    }
}

