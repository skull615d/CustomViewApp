package me.igorfedorov.myapp.feature.weather_screen.di

import me.igorfedorov.myapp.feature.weather_screen.data.WeatherRepository
import me.igorfedorov.myapp.feature.weather_screen.data.WeatherRepositoryImpl
import me.igorfedorov.myapp.feature.weather_screen.data.api.WeatherApi
import me.igorfedorov.myapp.feature.weather_screen.ui.WeatherScreenViewModel
import me.igorfedorov.myapp.feature.weather_screen.util.ApiKeyInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
private const val BASE_URL = "https://api.openweathermap.org/"
private const val API_KEY = "33ae2570581f89ec8a89d91abbcbc508"

val appModule = module {

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor(API_KEY))
            .addNetworkInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get<OkHttpClient>())
            .build()
    }

    single<WeatherApi> {
        get<Retrofit>().create(WeatherApi::class.java)
    }

    single<WeatherRepository> {
        WeatherRepositoryImpl(get<WeatherApi>())
    }

    viewModel {
        WeatherScreenViewModel(get<WeatherRepository>())
    }

}