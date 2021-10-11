package me.igorfedorov.myapp.feature.weather_screen.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.igorfedorov.myapp.feature.weather_screen.domain.WeatherInteractor

class WeatherScreenViewModel(
    private val interactor: WeatherInteractor
) : ViewModel() {

    val weather = MutableLiveData<String>()


    suspend fun requestWeather(cityName: String) {
        viewModelScope.launch {
            weather.postValue(interactor.getWeatherByCity(cityName).mainModel.temp.toString())
        }
    }

}