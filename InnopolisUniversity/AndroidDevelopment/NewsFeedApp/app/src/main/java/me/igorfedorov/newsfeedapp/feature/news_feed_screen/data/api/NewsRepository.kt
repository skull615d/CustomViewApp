package me.igorfedorov.newsfeedapp.feature.news_feed_screen.data.api

import kotlinx.coroutines.flow.Flow
import me.igorfedorov.newsfeedapp.common.Either
import me.igorfedorov.newsfeedapp.common.Resource
import me.igorfedorov.newsfeedapp.common.exception.CustomError
import me.igorfedorov.newsfeedapp.feature.news_feed_screen.domain.model.Article

interface NewsRepository {

    suspend fun getLastHourNews(): Flow<Resource<Either<CustomError, List<Article>>>>

}