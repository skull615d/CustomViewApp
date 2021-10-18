package me.igorfedorov.newsfeedapp.feature.news_feed_screen.data.api

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.igorfedorov.newsfeedapp.common.Either
import me.igorfedorov.newsfeedapp.common.Resource
import me.igorfedorov.newsfeedapp.common.exception.CustomError
import me.igorfedorov.newsfeedapp.common.utils.InternetAvailability
import me.igorfedorov.newsfeedapp.feature.news_feed_screen.data.api.model.toArticle
import me.igorfedorov.newsfeedapp.feature.news_feed_screen.domain.model.Article

class NewsRepositoryImpl(
    private val newsRemoteSource: NewsRemoteSource,
    private val internetAvailability: InternetAvailability
) : NewsRepository {
    override suspend fun getLastHourNews(): Flow<Resource<Either<CustomError, List<Article>>>> {
        return flow {
            when (internetAvailability.isNetworkAvailable()) {
                true -> {
                    try {
                        emit(Resource.Loading())
                        val either =
                            Either.Success(newsRemoteSource.getLastHourNews().articlesDTO.map { it.toArticle() })
                        emit(Resource.Either<Either<CustomError, List<Article>>>(data = either))
                    } catch (t: Throwable) {
                        emit(
                            Resource.Either<Either<CustomError, List<Article>>>(
                                Either.Failure(
                                    CustomError.ServerError()
                                )
                            )
                        )
                    }
                }
                false -> {
                    emit(
                        Resource.Either<Either<CustomError, List<Article>>>(
                            Either.Failure(
                                CustomError.NetworkConnection()
                            )
                        )
                    )

                }
            }
        }
    }
}

