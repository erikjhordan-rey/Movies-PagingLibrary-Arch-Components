package io.github.erikcaffrey.arch_components_paging_library.data.remote

import com.google.gson.annotations.SerializedName
import io.github.erikcaffrey.arch_components_paging_library.data.remote.Api.IMAGES_URL
import io.github.erikcaffrey.arch_components_paging_library.data.room.Movie

data class MovieApi(@SerializedName("id") val id: Int,
                    @SerializedName("title") val title: String,
                    @SerializedName("popularity") val popularity: Int,
                    @SerializedName("vote_average") val voteAverage: Int,
                    @SerializedName("poster_path") val posterPath: String,
                    @SerializedName("overview") val overview: String)

fun MovieApi.toMovieEntity() =
        this.run { Movie(id.toLong(), title, popularity, getVoteAverage(voteAverage), getPosterURL(posterPath), overview) }

private fun getVoteAverage(voteAverage: Int) = voteAverage * 10

private fun getPosterURL(posterPath: String) = IMAGES_URL + posterPath

