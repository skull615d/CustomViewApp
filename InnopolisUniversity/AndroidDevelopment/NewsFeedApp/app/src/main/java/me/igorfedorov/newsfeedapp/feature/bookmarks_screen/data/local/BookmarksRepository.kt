package me.igorfedorov.newsfeedapp.feature.bookmarks_screen.data.local

import me.igorfedorov.newsfeedapp.feature.news_feed_screen.domain.model.Article

interface BookmarksRepository {

    suspend fun create(article: Article)

    suspend fun read(): List<Article>

    suspend fun update(article: Article)

    suspend fun delete(article: Article)

}