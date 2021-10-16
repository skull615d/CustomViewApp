package me.igorfedorov.newsfeedapp.feature.main_screen.data.api

import me.igorfedorov.newsfeedapp.feature.main_screen.data.api.model.NewsDTO

class NewsRemoteSource(
    private val api: MainScreenNewsApi
) {

    suspend fun getLastHourNews(): NewsDTO = api.getAllLastHourNews()

}