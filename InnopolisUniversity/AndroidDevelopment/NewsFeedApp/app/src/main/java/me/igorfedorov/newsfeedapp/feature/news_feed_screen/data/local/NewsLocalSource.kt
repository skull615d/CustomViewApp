package me.igorfedorov.newsfeedapp.feature.news_feed_screen.data.local

import me.igorfedorov.newsfeedapp.feature.news_feed_screen.data.local.dao.ArticleDao
import me.igorfedorov.newsfeedapp.feature.news_feed_screen.data.local.entities.ArticleEntity

class NewsLocalSource(
    private val articleDao: ArticleDao
) {

    suspend fun create(articleEntity: ArticleEntity) = articleDao.insertArticle(articleEntity)

    suspend fun read(): List<ArticleEntity> = articleDao.getAllArticles()

    suspend fun update(articleEntity: ArticleEntity) = articleDao.updateArticle(articleEntity)

    suspend fun delete(articleEntity: ArticleEntity) = articleDao.deleteArticle(articleEntity)


}