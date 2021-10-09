package me.igorfedorov.myapp.feature.settings_dialog.data.api.model

import com.google.gson.annotations.SerializedName
import me.igorfedorov.myapp.feature.settings_dialog.domain.model.Cities

data class CitiesModel(
    @SerializedName("data")
    val citiesDataModel: List<CitiesDataModel>
)

fun CitiesModel.toCities() = Cities(
    citiesData = citiesDataModel.map { it.toCitiesData() }
)