package me.igorfedorov.newsfeedapp.feature.news_feed_screen.domain

import me.igorfedorov.newsfeedapp.base.attempt
import me.igorfedorov.newsfeedapp.feature.news_feed_screen.data.api.NewsRepository

class NewsFeedInteractor(
    private val repository: NewsRepository
) {

    suspend fun getNews() = attempt {
        repository.getLastHourNews()
    }


}