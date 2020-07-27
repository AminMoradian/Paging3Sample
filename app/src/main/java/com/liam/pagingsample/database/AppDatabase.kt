package com.liam.pagingsample.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.liam.pagingsample.data.daos.OwnerDao
import com.liam.pagingsample.data.daos.RepoDao
import com.liam.pagingsample.domain.OwnerEntity
import com.liam.pagingsample.domain.RepoEntity

@Database(
    entities = [
        RepoEntity::class,
        OwnerEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun repoDao(): RepoDao
    abstract fun ownerDao(): OwnerDao
}