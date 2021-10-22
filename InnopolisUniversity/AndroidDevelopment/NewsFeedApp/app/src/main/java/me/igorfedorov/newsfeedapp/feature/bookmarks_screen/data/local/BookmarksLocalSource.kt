package me.igorfedorov.newsfeedapp.feature.bookmarks_screen.data.local

import me.igorfedorov.newsfeedapp.feature.bookmarks_screen.data.local.dao.BookmarkDao
import me.igorfedorov.newsfeedapp.feature.bookmarks_screen.data.local.entities.BookmarkEntity

class BookmarksLocalSource(
    private val bookmarkDao: BookmarkDao
) {

    suspend fun create(bookmarkEntity: BookmarkEntity) = bookmarkDao.insertBookmark(bookmarkEntity)

    suspend fun read(): List<BookmarkEntity> = bookmarkDao.getAllBookmarks()

    suspend fun update(bookmarkEntity: BookmarkEntity) = bookmarkDao.updateBookmark(bookmarkEntity)

    suspend fun delete(bookmarkEntity: BookmarkEntity) = bookmarkDao.deleteBookmark(bookmarkEntity)


}