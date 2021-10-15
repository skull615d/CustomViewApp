package me.igorfedorov.newsfeedapp.feature.main_screen.data.api.model

data class NewsDTO(
    val articlesDTO: List<ArticleDTO>,
    val status: String,
    val totalResults: Int
)