package com.liam.pagingsample.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repos")
data class RepoEntity(
    var description: String?,
    var fork: Boolean,
    var fullName: String,
    @PrimaryKey
    var id: Int,
    var name: String,
    var node_id: String,
    var owner: Int,
    var `private`: Boolean,
    var url: String
) {
    constructor() : this(null, false, "", -1, "", "", -1, false, "")
}

@Entity(tableName = "owners")
data class OwnerEntity(
    var avatar_url: String,
    var gravatar_id: String,
    var html_url: String,
    @PrimaryKey
    var id: Int,
    var login: String,
    var node_id: String,
    var type: String,
    var url: String
) {
    constructor() : this("", "", "", -1, "", "", "", "")
}