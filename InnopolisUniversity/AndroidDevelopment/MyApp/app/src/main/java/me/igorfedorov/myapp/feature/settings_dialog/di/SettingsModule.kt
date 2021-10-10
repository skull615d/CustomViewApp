package me.igorfedorov.myapp.feature.settings_dialog.di

import me.igorfedorov.myapp.feature.settings_dialog.data.api.CitiesApi
import me.igorfedorov.myapp.feature.settings_dialog.data.api.CitiesRemoteSource
import me.igorfedorov.myapp.feature.settings_dialog.data.api.CitiesRepository
import me.igorfedorov.myapp.feature.settings_dialog.data.api.CitiesRepositoryImpl
import me.igorfedorov.myapp.feature.settings_dialog.domain.use_case.get_cities_data.GetCitiesDataUseCase
import me.igorfedorov.myapp.feature.settings_dialog.ui.SettingsDialogViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://geodb-free-service.wirefreethought.com/"
private const val OKHTTP_SETTINGS = "OKHTTP_SETTINGS"
private const val RETROFIT_SETTINGS = "RETROFIT_SETTINGS"

val settingsModule = module {

    single<OkHttpClient>(named(OKHTTP_SETTINGS)) {
        OkHttpClient.Builder()
            .addNetworkInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()
    }

    single<Retrofit>(named(RETROFIT_SETTINGS)) {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get<OkHttpClient>(qualifier = named(OKHTTP_SETTINGS)))
            .build()
    }

    single<CitiesApi> {
        get<Retrofit>(qualifier = named(RETROFIT_SETTINGS)).create(CitiesApi::class.java)
    }

    single<CitiesRemoteSource> {
        CitiesRemoteSource(get<CitiesApi>())
    }

    single<CitiesRepository> {
        CitiesRepositoryImpl(get<CitiesRemoteSource>())
    }

    factory<GetCitiesDataUseCase> {
        GetCitiesDataUseCase(get<CitiesRepository>())
    }

    viewModel<SettingsDialogViewModel> {
        SettingsDialogViewModel(get<GetCitiesDataUseCase>())
    }

}