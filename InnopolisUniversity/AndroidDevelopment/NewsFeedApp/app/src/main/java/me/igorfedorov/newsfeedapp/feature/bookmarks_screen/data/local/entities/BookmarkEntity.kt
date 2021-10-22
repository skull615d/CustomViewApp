package me.igorfedorov.newsfeedapp.feature.bookmarks_screen.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import me.igorfedorov.newsfeedapp.base.data_base.contracts.BookmarksContract

@Entity(
    tableName = BookmarksContract.TABLE_NAME,
    primaryKeys = [
        BookmarksContract.Columns.URL
    ]
)
data class BookmarkEntity(
    @ColumnInfo(name = BookmarksContract.Columns.URL)
    @PrimaryKey
    val url: String,
    @ColumnInfo(name = BookmarksContract.Columns.AUTHOR)
    val author: String?,
    @ColumnInfo(name = BookmarksContract.Columns.CONTENT)
    val content: String?,
    @ColumnInfo(name = BookmarksContract.Columns.DESCRIPTION)
    val description: String?,
    @ColumnInfo(name = BookmarksContract.Columns.PUBLISHED_AT)
    val publishedAt: String?,
    @ColumnInfo(name = BookmarksContract.Columns.SOURCE)
    val sourceEntity: SourceEntity?,
    @ColumnInfo(name = BookmarksContract.Columns.TITLE)
    val title: String?,
    @ColumnInfo(name = BookmarksContract.Columns.URL_TO_IMAGE)
    val urlToImage: String?
)
