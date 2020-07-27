package com.liam.pagingsample.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.liam.pagingsample.R
import com.liam.pagingsample.ui.adapter.RepoAdapter
import com.liam.pagingsample.ui.adapter.RepoLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var repoAdapter: RepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        repos.adapter = repoAdapter.withLoadStateFooter(
            footer = RepoLoadStateAdapter {
                repoAdapter.retry()
            }
        )

        refresh.setOnRefreshListener {
            repoAdapter.refresh()
        }

        retry.setOnClickListener {
            repoAdapter.retry()
        }

        lifecycleScope.launch {
            repoAdapter.loadStateFlow.collectLatest { loadState ->
                progress.isVisible = loadState.refresh is LoadState.Loading
                error.isVisible = loadState.refresh is LoadState.Error
                refresh.isRefreshing = loadState.refresh is LoadState.Loading
            }
        }

        lifecycleScope.launch {
            viewModel.flow.collectLatest {
                repoAdapter.submitData(it)
            }
        }
    }
}