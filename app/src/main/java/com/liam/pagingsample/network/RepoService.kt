package com.liam.pagingsample.network

import retrofit2.http.GET
import retrofit2.http.Query

interface RepoService {

    @GET("/repositories")
    suspend fun getPublicRepository(@Query("since") since: Int? = null): List<RepoRawResponse>
}

