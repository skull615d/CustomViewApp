package me.igorfedorov.newsfeedapp.feature.news_feed_screen.data.api

import me.igorfedorov.newsfeedapp.feature.news_feed_screen.data.api.model.toNews
import me.igorfedorov.newsfeedapp.feature.news_feed_screen.data.local.NewsLocalSource
import me.igorfedorov.newsfeedapp.feature.news_feed_screen.data.local.entities.toArticle
import me.igorfedorov.newsfeedapp.feature.news_feed_screen.data.local.entities.toArticleEntity
import me.igorfedorov.newsfeedapp.feature.news_feed_screen.domain.model.Article

class NewsRepositoryImpl(
    private val newsRemoteSource: NewsRemoteSource,
    private val newsLocalSource: NewsLocalSource
) : NewsRepository {

    override suspend fun getHeadlinesNews(): List<Article> {
        return newsRemoteSource.getHeadlinesNews().toNews().articles
    }

    override suspend fun addArticleToDB(article: Article) {
        newsLocalSource.create(article.toArticleEntity())
    }

    override suspend fun getArticlesFromDB(): List<Article> {
        return newsLocalSource.read().map { it.toArticle() }
    }

    override suspend fun updateArticleInDB(article: Article) {
        newsLocalSource.update(article.toArticleEntity())
    }

    override suspend fun deleteArticleFromDB(article: Article) {
        newsLocalSource.delete(article.toArticleEntity())
    }
}

