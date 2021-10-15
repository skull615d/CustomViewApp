package me.igorfedorov.newsfeedapp.feature.main_screen.domain.use_case.get_last_hour_news_use_case

import me.igorfedorov.newsfeedapp.feature.main_screen.data.api.NewsRepository
import me.igorfedorov.newsfeedapp.feature.main_screen.domain.model.News

class GetLastHourNewsUseCase(
    private val repository: NewsRepository
) {

    suspend operator fun invoke(): List<News> {
        return repository.getLastHourNews()
    }

}