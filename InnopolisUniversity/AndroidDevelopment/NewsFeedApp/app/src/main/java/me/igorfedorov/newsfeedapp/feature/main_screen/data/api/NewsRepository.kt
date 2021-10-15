package me.igorfedorov.newsfeedapp.feature.main_screen.data.api

import me.igorfedorov.newsfeedapp.feature.main_screen.domain.model.News

interface NewsRepository {

    suspend fun getLastHourNews(): List<News>

}