package com.liam.pagingsample.ui

import com.liam.pagingsample.domain.RepoEntity

sealed class UiModel {
    data class Repo(val repo: RepoEntity): UiModel()
    data class Separator(val name: String): UiModel()
}