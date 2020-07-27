package com.liam.pagingsample.domain

import androidx.paging.Pager
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface RepoRepository {

    fun getPublicRepoPaging(): Flow<PagingData<RepoEntity>>
}