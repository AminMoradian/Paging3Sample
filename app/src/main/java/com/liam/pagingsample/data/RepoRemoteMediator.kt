package com.liam.pagingsample.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.liam.pagingsample.data.daos.OwnerDao
import com.liam.pagingsample.data.daos.RepoDao
import com.liam.pagingsample.database.AppDatabase
import com.liam.pagingsample.domain.RepoEntity
import com.liam.pagingsample.network.RepoService
import com.liam.pagingsample.network.mappers.OwnerMapper
import com.liam.pagingsample.network.mappers.RepoMapper
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class RepoRemoteMediator @Inject constructor(
    private val repoDao: RepoDao,
    private val ownerDao: OwnerDao,
    private val repoService: RepoService,
    private val appDatabase: AppDatabase,
    private val repoMapper: RepoMapper,
    private val ownerMapper: OwnerMapper
) : RemoteMediator<Int, RepoEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RepoEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = true
                        )

                    lastItem.id
                }
            }

            val responses = repoService.getPublicRepository(since = loadKey)

            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    repoDao.clearAll()
                }

                val map = responses.map { repoMapper.mapToEntity(it) }
                val ownerMapped = responses.map { ownerMapper.mapToEntity(it.owner) }

                ownerDao.insert(ownerMapped)
                repoDao.insert(map)
            }

            val endOfPaginationReached = responses.isEmpty()
            MediatorResult.Success(
                endOfPaginationReached = endOfPaginationReached
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}