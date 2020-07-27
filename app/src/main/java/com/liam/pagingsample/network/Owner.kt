package com.liam.pagingsample.network


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Owner(
    @Json(name = "avatar_url")
    val avatarUrl: String,
    @Json(name = "gravatar_id")
    val gravatarId: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "login")
    val login: String,
    @Json(name = "node_id")
    val nodeId: String,
    @Json(name = "site_admin")
    val siteAdmin: Boolean,
    @Json(name = "type")
    val type: String,
    @Json(name = "url")
    val url: String
)