package me.igorfedorov.myapp.feature.settings_dialog.domain.use_case.get_cities_data

import me.igorfedorov.myapp.feature.settings_dialog.data.api.CitiesRepository

class GetCitiesDataUseCase(
    private val repository: CitiesRepository
) {

    operator fun invoke(cityName: String) = repository.getCitiesData(cityName)

}