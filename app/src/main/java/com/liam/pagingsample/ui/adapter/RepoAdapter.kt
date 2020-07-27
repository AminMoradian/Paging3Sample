package com.liam.pagingsample.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.liam.pagingsample.R
import com.liam.pagingsample.ui.UiModel
import kotlinx.android.synthetic.main.item_repo.view.*
import kotlinx.android.synthetic.main.item_sep.view.*
import javax.inject.Inject

class RepoAdapter @Inject constructor() :
    PagingDataAdapter<UiModel, RecyclerView.ViewHolder>(RepoDiffUtil) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SeparatorViewHolder -> holder.bind(getItem(position)!! as UiModel.Separator)
            is RepoViewHolder -> holder.bind(getItem(position)!! as UiModel.Repo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            1 -> RepoViewHolder(parent)
            2 -> SeparatorViewHolder(parent)
            else -> throw IllegalArgumentException("unknown view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is UiModel.Repo -> 1
            is UiModel.Separator -> 2
            null -> -1
        }
    }

    inner class SeparatorViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sep, parent, false)
    ) {
        fun bind(item: UiModel.Separator) {
            itemView.textViewSep.text = item.name
        }
    }

    inner class RepoViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_repo, parent, false)
    ) {
        fun bind(item: UiModel.Repo) {
            itemView.repoName.text = item.repo.name
            itemView.repoUrl.text = item.repo.url
        }
    }

    object RepoDiffUtil : DiffUtil.ItemCallback<UiModel>() {

        override fun areItemsTheSame(oldItem: UiModel, newItem: UiModel): Boolean {
            return when {
                oldItem == newItem -> true
                (oldItem is UiModel.Repo && newItem is UiModel.Repo) -> {
                    oldItem.repo.id == newItem.repo.id
                }
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: UiModel, newItem: UiModel): Boolean {
            return oldItem == newItem
        }
    }
}