package com.liam.pagingsample.di

import com.liam.pagingsample.data.RepoRepositoryImpl
import com.liam.pagingsample.domain.RepoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindRepoRepository(repositoryImpl: RepoRepositoryImpl): RepoRepository
}