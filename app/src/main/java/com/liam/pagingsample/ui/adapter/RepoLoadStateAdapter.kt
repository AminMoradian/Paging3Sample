package com.liam.pagingsample.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.liam.pagingsample.R
import kotlinx.android.synthetic.main.item_load_state.view.*

class RepoLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<RepoLoadStateAdapter.RepoLoadStateViewHolder>() {

    override fun onBindViewHolder(holder: RepoLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): RepoLoadStateViewHolder {
        return RepoLoadStateViewHolder(parent, retry)
    }

    class RepoLoadStateViewHolder(
        parent: ViewGroup,
        private val retry: () -> Unit
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_load_state, parent, false
        )
    ) {
        fun bind(loadState: LoadState) {
            itemView.progress.isVisible = loadState == LoadState.Loading
            itemView.retry.isVisible = loadState != LoadState.Loading

            itemView.retry.setOnClickListener {
                retry()
            }
        }
    }
}