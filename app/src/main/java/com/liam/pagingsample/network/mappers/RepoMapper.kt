package com.liam.pagingsample.network.mappers

import com.liam.pagingsample.domain.RepoEntity
import com.liam.pagingsample.network.RepoRawResponse
import javax.inject.Inject

class RepoMapper @Inject constructor() : Mapper<RepoRawResponse, RepoEntity> {
    override fun mapToEntity(m: RepoRawResponse): RepoEntity {
        return RepoEntity(
            m.description,
            m.fork,
            m.fullName,
            m.id,
            m.name,
            m.nodeId,
            m.owner.id,
            m.private,
            m.url
        )
    }
}