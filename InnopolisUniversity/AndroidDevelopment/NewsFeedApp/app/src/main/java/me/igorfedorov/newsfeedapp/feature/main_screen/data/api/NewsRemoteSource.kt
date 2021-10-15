package me.igorfedorov.newsfeedapp.feature.main_screen.data.api

class NewsRemoteSource(
    private val api: MainScreenNewsApi
) {

    suspend fun getLastHourNews() = api.getAllLastHourNews()

}