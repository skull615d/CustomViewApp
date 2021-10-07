package me.igorfedorov.myapp.feature.weather_screen.ui

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.igorfedorov.myapp.R
import me.igorfedorov.myapp.common.Resource
import me.igorfedorov.myapp.feature.weather_screen.data.model.WeatherFromApi
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherScreenActivity : AppCompatActivity() {

    private val weatherViewModel: WeatherScreenViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_screen)

        val weatherButton = findViewById<Button>(R.id.getWeatherButton)
        weatherButton.setOnClickListener {
            weatherViewModel.getWeatherByCityName("London")
        }


        val weatherText = findViewById<TextView>(R.id.weatherTextView)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        CoroutineScope(Dispatchers.IO).launch {
            weatherViewModel.weather.collect {
                updateProgressBar(it, progressBar)
                when (it) {
                    is Resource.Success -> {
                        weatherText.text = it.data?.main?.temp?.toString() ?: "Some Error"
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

    private fun updateProgressBar(resource: Resource<WeatherFromApi>, progressBar: ProgressBar) {
        CoroutineScope(Dispatchers.Main).launch {
            progressBar.isVisible = resource is Resource.Loading
        }
    }
}