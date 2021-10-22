package me.igorfedorov.newsfeedapp.feature.bookmarks_screen.data.local.dao

import androidx.room.*
import me.igorfedorov.newsfeedapp.base.data_base.contracts.BookmarksContract
import me.igorfedorov.newsfeedapp.feature.bookmarks_screen.data.local.entities.BookmarkEntity

@Dao
interface BookmarkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(bookmarkEntity: BookmarkEntity)

    @Query("SELECT * FROM ${BookmarksContract.TABLE_NAME}")
    suspend fun getAllBookmarks(): List<BookmarkEntity>

    @Update
    suspend fun updateBookmark(bookmarkEntity: BookmarkEntity)

    @Delete
    suspend fun deleteBookmark(bookmarkEntity: BookmarkEntity)

}