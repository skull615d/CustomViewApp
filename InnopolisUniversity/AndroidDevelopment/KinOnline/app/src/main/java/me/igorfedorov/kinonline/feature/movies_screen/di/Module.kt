package me.igorfedorov.kinonline.feature.movies_screen.di

import me.igorfedorov.kinonline.feature.movies_screen.data.MoviesRepository
import me.igorfedorov.kinonline.feature.movies_screen.data.MoviesRepositoryImpl
import me.igorfedorov.kinonline.feature.movies_screen.data.api.MoviesApi
import me.igorfedorov.kinonline.feature.movies_screen.data.api.MoviesRemoteSource
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val moviesScreenModule = module {

    single<MoviesApi> {
        get<Retrofit>().create()
    }

    single<MoviesRemoteSource> {
        MoviesRemoteSource(get<MoviesApi>())
    }

    single<MoviesRepository> {
        MoviesRepositoryImpl(get<MoviesRemoteSource>())
    }

}