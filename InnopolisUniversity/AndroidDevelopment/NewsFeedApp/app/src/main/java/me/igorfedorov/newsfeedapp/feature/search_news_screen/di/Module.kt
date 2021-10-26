package me.igorfedorov.newsfeedapp.feature.search_news_screen.di

import me.igorfedorov.newsfeedapp.feature.search_news_screen.data.SearchNewsRepository
import me.igorfedorov.newsfeedapp.feature.search_news_screen.data.SearchNewsRepositoryImpl
import me.igorfedorov.newsfeedapp.feature.search_news_screen.data.api.SearchNewsApi
import me.igorfedorov.newsfeedapp.feature.search_news_screen.data.api.SearchNewsRemoteSource
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val searchNewsModule = module {

    single<SearchNewsApi> {
        get<Retrofit>().create()
    }

    single<SearchNewsRemoteSource> {
        SearchNewsRemoteSource(get<SearchNewsApi>())
    }

    single<SearchNewsRepository> {
        SearchNewsRepositoryImpl(get<SearchNewsRemoteSource>())
    }

}