package me.igorfedorov.myapp.feature.settings_screen.domain.use_case.get_cities_data

import me.igorfedorov.myapp.feature.settings_screen.data.api.CitiesRepository

class GetCitiesDataUseCase(
    private val repository: CitiesRepository
) {

    suspend operator fun invoke(cityName: String) = repository.getCitiesData(cityName)

}