package me.igorfedorov.newsfeedapp.feature.news_feed_screen.domain

import me.igorfedorov.newsfeedapp.base.utils.attempt
import me.igorfedorov.newsfeedapp.feature.bookmarks_screen.data.local.BookmarksRepository
import me.igorfedorov.newsfeedapp.feature.news_feed_screen.data.api.NewsRepository
import me.igorfedorov.newsfeedapp.feature.news_feed_screen.domain.model.Article

class NewsFeedInteractor(
    private val newsRepository: NewsRepository,
    private val bookmarksRepository: BookmarksRepository
) {

    suspend fun getHeadlinesNews() = attempt {
        val news = newsRepository.getHeadlinesNews()
        val bookmarks = bookmarksRepository.read()
        news.map { article ->
            if (bookmarks.map { bookmarksArticle ->
                    bookmarksArticle.url
                }.contains(article.url)) {
                article.copy(isBookmarked = true)
            } else {
                article.copy(isBookmarked = false)
            }
        }
    }

    suspend fun addArticleToBookmarks(article: Article) {
        bookmarksRepository.create(article.copy(isBookmarked = true))
    }

    suspend fun deleteArticleFromBookmarks(article: Article) {
        bookmarksRepository.delete(article)
    }


}