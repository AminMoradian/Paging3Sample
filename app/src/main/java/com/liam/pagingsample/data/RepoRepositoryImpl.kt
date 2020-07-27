package com.liam.pagingsample.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.liam.pagingsample.data.daos.RepoDao
import com.liam.pagingsample.domain.RepoEntity
import com.liam.pagingsample.domain.RepoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepoRepositoryImpl @Inject constructor(
    private val repoRemoteMediator: RepoRemoteMediator,
    private val repoDao: RepoDao
) : RepoRepository {

    override fun getPublicRepoPaging(): Flow<PagingData<RepoEntity>> = Pager(
        config = PagingConfig(pageSize = 50),
        remoteMediator = repoRemoteMediator
    ) {
        repoDao.pagingSource()
    }.flow
}