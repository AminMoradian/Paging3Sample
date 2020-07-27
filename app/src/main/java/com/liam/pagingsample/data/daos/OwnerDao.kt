package com.liam.pagingsample.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.liam.pagingsample.domain.OwnerEntity

@Dao
interface OwnerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ownerEntity: OwnerEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ownerEntities: List<OwnerEntity>)

    @Query("SELECT * FROM owners WHERE id = :id")
    suspend fun getOwnerById(id: Int): OwnerEntity?
}