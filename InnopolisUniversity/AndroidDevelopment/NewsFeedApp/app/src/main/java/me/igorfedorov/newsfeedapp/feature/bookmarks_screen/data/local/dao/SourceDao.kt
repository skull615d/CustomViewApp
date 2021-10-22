package me.igorfedorov.newsfeedapp.feature.bookmarks_screen.data.local.dao

import androidx.room.*
import me.igorfedorov.newsfeedapp.base.data_base.contracts.BookmarksContract
import me.igorfedorov.newsfeedapp.feature.bookmarks_screen.data.local.entities.SourceEntity

@Dao
interface SourceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSource(sourceEntity: SourceEntity)

    @Query("SELECT * FROM ${BookmarksContract.TABLE_NAME}")
    suspend fun getAllSource(): List<SourceEntity>

    @Update
    suspend fun updateSource(sourceEntity: SourceEntity)

    @Delete
    suspend fun deleteSource(sourceEntity: SourceEntity)

}