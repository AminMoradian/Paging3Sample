package com.liam.pagingsample.network.mappers

interface Mapper<in M, out E> {

    fun mapToEntity(m: M): E
}