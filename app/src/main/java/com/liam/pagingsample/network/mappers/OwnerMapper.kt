package com.liam.pagingsample.network.mappers

import com.liam.pagingsample.domain.OwnerEntity
import com.liam.pagingsample.network.Owner
import javax.inject.Inject

class OwnerMapper @Inject constructor() : Mapper<Owner, OwnerEntity> {

    override fun mapToEntity(m: Owner): OwnerEntity {
        return OwnerEntity(
            m.avatarUrl,
            m.gravatarId,
            m.avatarUrl,
            m.id,
            m.login,
            m.nodeId,
            m.type,
            m.url
        )
    }
}