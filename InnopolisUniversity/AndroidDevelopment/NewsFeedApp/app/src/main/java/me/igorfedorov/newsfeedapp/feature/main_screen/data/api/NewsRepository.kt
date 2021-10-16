package me.igorfedorov.newsfeedapp.feature.main_screen.data.api

import me.igorfedorov.newsfeedapp.feature.main_screen.domain.model.Article

interface NewsRepository {

    suspend fun getLastHourNews(): List<Article>

}