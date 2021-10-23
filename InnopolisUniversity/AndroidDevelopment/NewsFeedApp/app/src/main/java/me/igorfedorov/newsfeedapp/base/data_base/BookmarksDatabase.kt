package me.igorfedorov.newsfeedapp.base.data_base

import androidx.room.Database
import androidx.room.RoomDatabase
import me.igorfedorov.newsfeedapp.feature.bookmarks_screen.data.local.dao.BookmarkDao
import me.igorfedorov.newsfeedapp.feature.bookmarks_screen.data.local.entities.BookmarkEntity


@Database(
    entities = [
        BookmarkEntity::class
    ],
    version = BookmarksDatabase.DB_VERSION
)
abstract class BookmarksDatabase : RoomDatabase() {

    companion object {

        const val DB_VERSION = 1
        const val DB_NAME = "bookmarks_database"
    }

    abstract fun bookmarksDao(): BookmarkDao

}
