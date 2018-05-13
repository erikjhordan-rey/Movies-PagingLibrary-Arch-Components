package io.github.erikcaffrey.arch_components_paging_library.view

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import io.github.erikcaffrey.arch_components_paging_library.data.room.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun render(movie: Movie) = itemView.run {
        Glide.with(image.context).load(movie.posterUrl).into(image)
    }

    fun clear() = itemView.run {
        image.invalidate()
    }
}
