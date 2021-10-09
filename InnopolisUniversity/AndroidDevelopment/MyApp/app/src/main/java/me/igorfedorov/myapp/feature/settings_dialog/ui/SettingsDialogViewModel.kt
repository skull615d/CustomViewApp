package me.igorfedorov.myapp.feature.settings_dialog.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import me.igorfedorov.myapp.common.Resource
import me.igorfedorov.myapp.feature.settings_dialog.domain.model.CitiesData
import me.igorfedorov.myapp.feature.settings_dialog.domain.use_case.get_cities_data.GetCitiesDataUseCase

class SettingsDialogViewModel(
    private val getCitiesDataUseCase: GetCitiesDataUseCase
) : ViewModel() {

    private val _citiesData = MutableStateFlow<Resource<List<CitiesData>>>(Resource.Initialized())
    val citiesData: StateFlow<Resource<List<CitiesData>>>
        get() = _citiesData

    @ExperimentalCoroutinesApi
    @FlowPreview
    fun getCitiesData(cityNameFlow: Flow<String>) {
//        getCitiesDataUseCase(cityName).onEach {
//            _citiesData.value = it
//        }
        cityNameFlow
            .debounce(500)
            .mapLatest { cityName ->
                getCitiesDataUseCase(cityName).onEach {
                    _citiesData.value = it
                }
            }
            .launchIn(viewModelScope)
    }
}