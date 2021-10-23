package me.igorfedorov.newsfeedapp.feature.bookmarks_screen.domain

import me.igorfedorov.newsfeedapp.base.utils.attempt
import me.igorfedorov.newsfeedapp.feature.bookmarks_screen.data.local.BookmarksRepository
import me.igorfedorov.newsfeedapp.feature.news_feed_screen.domain.model.Article

class BookmarksInteractor(
    private val bookmarksRepository: BookmarksRepository
) {

    suspend fun create(article: Article) = bookmarksRepository.create(article)

    suspend fun read() = attempt {
        bookmarksRepository.read()
    }

    suspend fun update(article: Article) = bookmarksRepository.update(article)

    suspend fun delete(article: Article) = attempt {
        bookmarksRepository.delete(article)
    }

}
