package io.github.erikjhordanrey.arch_components_paging_library.data.room

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.erikjhordanrey.arch_components_paging_library.data.room.DATABASE.SELECT_MOVIE

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieList: List<Movie>)

    @Query(SELECT_MOVIE)
    fun allMovies(): DataSource.Factory<Int, Movie>
}
