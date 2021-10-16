package me.igorfedorov.newsfeedapp.feature.main_screen.data.api

import me.igorfedorov.newsfeedapp.common.Either
import me.igorfedorov.newsfeedapp.common.exception.CustomError
import me.igorfedorov.newsfeedapp.feature.main_screen.data.api.model.toArticle
import me.igorfedorov.newsfeedapp.feature.main_screen.domain.model.Article

class NewsRepositoryImpl(
    private val newsRemoteSource: NewsRemoteSource
) : NewsRepository {

    override suspend fun getLastHourNews(): Either<CustomError, List<Article>> {
        return try {
            Either.Success(newsRemoteSource.getLastHourNews().articlesDTO.map { it.toArticle() })
        } catch (t: Throwable) {
            Either.Failure(CustomError.ServerError())
        }
    }
}

