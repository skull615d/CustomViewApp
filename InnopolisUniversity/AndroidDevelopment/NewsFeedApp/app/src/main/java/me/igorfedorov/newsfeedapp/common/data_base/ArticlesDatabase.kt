package me.igorfedorov.newsfeedapp.common.data_base

import androidx.room.Database
import androidx.room.RoomDatabase
import me.igorfedorov.newsfeedapp.common.data_base.dao.ArticlesDao
import me.igorfedorov.newsfeedapp.feature.news_feed_screen.domain.model.Article


@Database(
    entities = [
        Article::class
    ],
    version = ArticlesDatabase.DB_VERSION
)
abstract class ArticlesDatabase : RoomDatabase() {

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "articles_database"
    }

    abstract fun articlesDao(): ArticlesDao

}