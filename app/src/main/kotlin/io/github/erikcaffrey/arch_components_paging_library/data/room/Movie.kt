package io.github.erikcaffrey.arch_components_paging_library.data.room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import io.github.erikcaffrey.arch_components_paging_library.data.room.DATABASE.TABLE_MOVIE

@Entity(tableName = TABLE_MOVIE)
data class Movie(@PrimaryKey val id: Long,
                 val title: String,
                 val popularity: Int,
                 val voteAverage: Int,
                 val posterUrl: String,
                 val description: String)
