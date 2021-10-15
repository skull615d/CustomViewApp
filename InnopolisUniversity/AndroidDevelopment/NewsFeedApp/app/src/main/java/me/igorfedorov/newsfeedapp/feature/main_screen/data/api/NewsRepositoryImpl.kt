package me.igorfedorov.newsfeedapp.feature.main_screen.data.api

import me.igorfedorov.newsfeedapp.feature.main_screen.data.api.model.toNews
import me.igorfedorov.newsfeedapp.feature.main_screen.domain.model.News

class NewsRepositoryImpl(
    private val newsRemoteSource: NewsRemoteSource
) : NewsRepository {

    override suspend fun getLastHourNews(): List<News> {
        return newsRemoteSource.getLastHourNews().map { it.toNews() }
    }

}