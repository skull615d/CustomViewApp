package me.igorfedorov.newsfeedapp.feature.main_screen.data.api

import me.igorfedorov.newsfeedapp.feature.main_screen.data.api.model.NewsDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface MainScreenNewsApi {

    @GET("v2/everything")
    suspend fun getAllLastHourNews(
        // 3_600_000 = 1 Hour :)
        @Query("from") from: String = "${System.currentTimeMillis() - 3_600_000L}"
    ): List<NewsDTO>

}