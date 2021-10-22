package me.igorfedorov.newsfeedapp.feature.bookmarks_screen.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import me.igorfedorov.newsfeedapp.base.data_base.contracts.SourceContract

@Entity(tableName = SourceContract.TABLE_NAME)
data class SourceEntity(
    @ColumnInfo(name = SourceContract.Columns.ID)
    val id: String?,
    @ColumnInfo(name = SourceContract.Columns.NAME)
    val name: String?
)