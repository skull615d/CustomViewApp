package me.igorfedorov.myapp.feature.settings_dialog.data.api.model

import me.igorfedorov.myapp.feature.settings_dialog.domain.model.Cities
import me.igorfedorov.myapp.feature.settings_dialog.domain.model.CitiesData


fun CitiesDataModel.toCitiesData() = CitiesData(
    city = city,
    country = country,
    name = name
)

fun CitiesModel.toCities() = Cities(
    citiesData = citiesDataModel.map { it.toCitiesData() }
)