package me.igorfedorov.myapp.feature.weather_screen.ui

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import me.igorfedorov.myapp.common.Resource
import me.igorfedorov.myapp.feature.weather_screen.data.api.model.toWeatherMain
import me.igorfedorov.myapp.feature.weather_screen.domain.WeatherInteractor
import me.igorfedorov.myapp.feature.weather_screen.domain.model.WeatherMain
import me.igorfedorov.myapp.feature.weather_screen.domain.use_case.get_weather_by_city_use_case.GetWeatherByCityUseCase
import retrofit2.HttpException
import java.io.IOException

class WeatherScreenViewModel(
    private val interactor: WeatherInteractor,
    private val getWeatherByCityUseCase: GetWeatherByCityUseCase
) : ViewModel() {

    private val _weather = MutableStateFlow<Resource<WeatherMain>>(Resource.Initialized())
    val weather: StateFlow<Resource<WeatherMain>>
        get() = _weather

    suspend fun requestWeather(cityName: String) {
        viewModelScope.launch {
            interactor.getWeatherByCity(cityName)
        }
    }

    fun requestWeatherByCity(cityName: String) {
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

    @SuppressLint("MissingPermission")
    fun getCurrentLocation(context: Context, cityName: String?) {
        viewModelScope.launch {
            LocationServices.getFusedLocationProviderClient(context)
                .lastLocation
                .addOnSuccessListener {
                    requestWeatherByCity(
                        cityName
                            ?: getCityNameFromLocation(context, it)
                    )
                }
        }
    }

    private fun getCityNameFromLocation(context: Context, location: Location?): String {
        return location?.let {
            Geocoder(context).getFromLocation(
                it.latitude,
                it.longitude,
                1
            )
                .first()
                .locality
        }
            ?: ""
    }
}

