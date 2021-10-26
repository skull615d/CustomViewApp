package me.igorfedorov.newsfeedapp.feature.search_news_screen.ui.utils

enum class SortOrder(val apiName: String) {
    RELEVANCY(apiName = "relevancy"),
    POPULARITY(apiName = "popularity"),
    PUBLISHED_AT(apiName = "publishedAt")
}