package io.github.erikcaffrey.arch_components_paging_library.view

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import io.github.erikcaffrey.arch_components_paging_library.R
import io.github.erikcaffrey.arch_components_paging_library.data.room.Movie

class MoviesPagedListAdapter : PagedListAdapter<Movie, MoviesViewHolder>(movieDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MoviesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val article = getItem(position)
        if (article != null) {
            holder.render(article)
        } else {
            holder.clear()
        }
    }

    companion object {
        private val movieDiffCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
}
