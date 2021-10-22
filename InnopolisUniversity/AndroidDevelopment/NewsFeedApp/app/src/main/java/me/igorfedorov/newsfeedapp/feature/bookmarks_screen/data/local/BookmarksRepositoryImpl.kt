package me.igorfedorov.newsfeedapp.feature.bookmarks_screen.data.local

import me.igorfedorov.newsfeedapp.feature.bookmarks_screen.data.local.entities.toArticle
import me.igorfedorov.newsfeedapp.feature.bookmarks_screen.data.local.entities.toBookmark
import me.igorfedorov.newsfeedapp.feature.news_feed_screen.domain.model.Article

class BookmarksRepositoryImpl(
    private val source: BookmarksLocalSource
) : BookmarksRepository {

    override suspend fun create(article: Article) {
        source.create(article.toBookmark())
    }

    override suspend fun read(): List<Article> {
        return source.read().map { it.toArticle() }
    }

    override suspend fun update(article: Article) {
        source.update(article.toBookmark())
    }

    override suspend fun delete(article: Article) {
        source.delete(article.toBookmark())
    }
}