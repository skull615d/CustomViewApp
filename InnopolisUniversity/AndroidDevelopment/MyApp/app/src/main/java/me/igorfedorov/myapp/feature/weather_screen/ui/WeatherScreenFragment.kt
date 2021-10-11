package me.igorfedorov.myapp.feature.weather_screen.ui

import android.annotation.SuppressLint
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.navArgs
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import me.igorfedorov.myapp.R
import me.igorfedorov.myapp.common.Resource
import me.igorfedorov.myapp.databinding.FragmentWeatherScreenBinding
import me.igorfedorov.myapp.feature.weather_screen.di.VIEW_MODEL_WEATHER
import me.igorfedorov.myapp.feature.weather_screen.domain.model.WeatherMain
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import permissions.dispatcher.ktx.LocationPermission
import permissions.dispatcher.ktx.constructLocationPermissionRequest

class WeatherScreenFragment : Fragment(R.layout.fragment_weather_screen) {

    private val weatherViewModel: WeatherScreenViewModel by viewModel(
        qualifier = named(
            VIEW_MODEL_WEATHER
        )
    )

    private var _binding: FragmentWeatherScreenBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("Cannot access binding")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                        LocationServices.getFusedLocationProviderClient(requireContext())
                            .lastLocation
                            .addOnSuccessListener {
                                weatherViewModel.getWeatherByCity(
                                    navArgs<WeatherScreenFragmentArgs>().value.cityName
                                        ?: getCityNameFromLocation(it)
                                )
                            }
                    }
                }
            ).launch()
        }
    }

    private fun getCityNameFromLocation(location: Location?): String {
        return location?.let {
            Geocoder(requireContext()).getFromLocation(
                it.latitude,
                it.longitude,
                1
            )
                .first()
                .locality
        }
            ?: ""
    }

    private fun observeViewModel() {
        weatherViewModel.weather.onEach {
            updateProgressBar(it, binding.progressBarWeather)
            when (it) {
                is Resource.Success -> {
                    binding.weatherTextView.text = """
                            temp = ${it.data?.main?.temp?.toString()}
                            city = ${it.data?.name}
                        """.trimMargin()
                }
                is Resource.Error -> {
                    binding.weatherTextView.text = it.message
                }
                is Resource.Loading -> {
                }
                is Resource.Initialized -> {

                }
            }
        }.launchIn(viewLifecycleOwner.lifecycle.coroutineScope)
    }

    private fun initWeatherButton() {
        binding.getWeatherButton.setOnClickListener {
            weatherViewModel.getWeatherByCity("London")
        }
    }

    private fun updateProgressBar(resource: Resource<WeatherMain>, progressBar: ProgressBar) {
        CoroutineScope(Dispatchers.Main).launch {
            progressBar.isVisible = resource is Resource.Loading
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}