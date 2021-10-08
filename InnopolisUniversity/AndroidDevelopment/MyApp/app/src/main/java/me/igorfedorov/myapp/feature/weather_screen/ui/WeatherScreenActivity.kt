package me.igorfedorov.myapp.feature.weather_screen.ui

import android.annotation.SuppressLint
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.igorfedorov.myapp.R
import me.igorfedorov.myapp.common.Resource
import me.igorfedorov.myapp.feature.weather_screen.domain.model.WeatherMain
import org.koin.androidx.viewmodel.ext.android.viewModel
import permissions.dispatcher.ktx.LocationPermission
import permissions.dispatcher.ktx.constructLocationPermissionRequest

class WeatherScreenActivity : AppCompatActivity() {

    private val weatherViewModel: WeatherScreenViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_screen)

        initWeatherButton()

        observeViewModel()

        getWeatherForCurrentLocation()

    }

    @SuppressLint("MissingPermission")
    private fun getWeatherForCurrentLocation() {
        CoroutineScope(Dispatchers.Main).launch {
            constructLocationPermissionRequest(
                permissions = arrayOf(LocationPermission.FINE, LocationPermission.COARSE),
                requiresPermission = {
                    CoroutineScope(Dispatchers.IO).launch {
                        LocationServices.getFusedLocationProviderClient(this@WeatherScreenActivity)
                            .lastLocation
                            .addOnSuccessListener {
                                weatherViewModel.getWeatherByCityName(getCityNameFromLocation(it))
                            }
                    }
                }
            ).launch()
        }
    }

    private fun getCityNameFromLocation(location: Location?): String {
        return location?.let {
            Geocoder(this).getFromLocation(
                it.latitude,
                it.longitude,
                1
            )
                .first()
                .locality
        } ?: ""
    }

    @SuppressLint("SetTextI18n")
    private fun observeViewModel() {
        val weatherText = findViewById<TextView>(R.id.weatherTextView)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        CoroutineScope(Dispatchers.Main).launch {
            weatherViewModel.weather.collect {
                updateProgressBar(it, progressBar)
                when (it) {
                    is Resource.Success -> {
                        weatherText.text = """
                            temp = ${it.data?.main?.temp?.toString()}
                            city = ${it.data?.name}
                        """.trimMargin()
                    }
                    is Resource.Error -> {
                        weatherText.text = it.message
                    }
                    is Resource.Loading -> {
                    }
                    is Resource.Initialized -> {

                    }
                }
            }
        }
    }

    private fun initWeatherButton() {
        val weatherButton = findViewById<Button>(R.id.getWeatherButton)
        weatherButton.setOnClickListener {
            weatherViewModel.getWeatherByCityName("London")
        }
    }

    private fun updateProgressBar(resource: Resource<WeatherMain>, progressBar: ProgressBar) {
        CoroutineScope(Dispatchers.Main).launch {
            progressBar.isVisible = resource is Resource.Loading
        }
    }
}