package com.liam.pagingsample.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.liam.pagingsample.domain.RepoRepository
import kotlinx.coroutines.flow.map

class MainViewModel @ViewModelInject constructor(
    private val repoRepository: RepoRepository
) : ViewModel() {

    val flow = repoRepository.getPublicRepoPaging().cachedIn(viewModelScope)
        .map { pagingData ->
            pagingData.map { UiModel.Repo(it) }
        }.map {
            it.insertSeparators<UiModel.Repo, UiModel> {
                    before: UiModel.Repo?, after: UiModel.Repo? ->

                val beforeName = before?.repo?.name
                val afterName = after?.repo?.name

                if (beforeName != null && afterName != null) {
                    if (beforeName.first() == afterName.first())
                        UiModel.Separator(beforeName.first().toString())
                    else
                        null
                } else
                    null
            }
        }
}