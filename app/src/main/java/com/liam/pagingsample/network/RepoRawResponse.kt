package com.liam.pagingsample.network


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RepoRawResponse(
    @Json(name = "description")
    val description: String?,
    @Json(name = "fork")
    val fork: Boolean,
    @Json(name = "full_name")
    val fullName: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "node_id")
    val nodeId: String,
    @Json(name = "owner")
    val owner: Owner,
    @Json(name = "private")
    val `private`: Boolean,
    @Json(name = "url")
    val url: String
)