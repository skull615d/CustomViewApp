package me.igorfedorov.newsfeedapp.feature.news_feed_screen.data.api

import me.igorfedorov.newsfeedapp.common.Either
import me.igorfedorov.newsfeedapp.common.exception.CustomError
import me.igorfedorov.newsfeedapp.common.utils.InternetAvailability
import me.igorfedorov.newsfeedapp.feature.news_feed_screen.data.api.model.toArticle
import me.igorfedorov.newsfeedapp.feature.news_feed_screen.domain.model.Article

class NewsRepositoryImpl(
    private val newsRemoteSource: NewsRemoteSource,
    private val internetAvailability: InternetAvailability
) : NewsRepository {
    override suspend fun getLastHourNews(): Either<CustomError, List<Article>> {
        return when (internetAvailability.isNetworkAvailable()) {
            true -> {
                try {
                    Either.Success(newsRemoteSource.getLastHourNews().articlesDTO.map { it.toArticle() })
                } catch (t: Throwable) {
                    Either.Failure(CustomError.ServerError())
                }
            }
            false -> {
                Either.Failure(CustomError.NetworkConnection())
            }
        }
    }
}

