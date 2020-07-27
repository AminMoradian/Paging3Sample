package com.liam.pagingsample.data.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.liam.pagingsample.domain.RepoEntity

@Dao
interface RepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(repoEntity: RepoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(repoEntities: List<RepoEntity>)

    @Query("SELECT * FROM repos")
    fun pagingSource(): PagingSource<Int, RepoEntity>

    @Query("DELETE FROM repos")
    suspend fun clearAll()
}