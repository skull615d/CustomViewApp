package me.igorfedorov.newsfeedapp.feature.main_screen.data.api

import me.igorfedorov.newsfeedapp.feature.main_screen.data.api.model.toArticle
import me.igorfedorov.newsfeedapp.feature.main_screen.domain.model.Article

class NewsRepositoryImpl(
    private val newsRemoteSource: NewsRemoteSource
) : NewsRepository {

    override suspend fun getLastHourNews(): List<Article> {
        return newsRemoteSource.getLastHourNews().articlesDTO.map { it.toArticle() }
    }

}