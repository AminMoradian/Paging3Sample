package com.liam.pagingsample.di

import android.content.Context
import androidx.room.Room
import com.liam.pagingsample.data.daos.OwnerDao
import com.liam.pagingsample.data.daos.RepoDao
import com.liam.pagingsample.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DBModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app.db").build()
    }

    @Provides
    fun provideRepoDao(appDatabase: AppDatabase): RepoDao = appDatabase.repoDao()

    @Provides
    fun provideOwnerDao(appDatabase: AppDatabase): OwnerDao = appDatabase.ownerDao()
}