package io.github.erikjhordanrey.arch_components_paging_library.data.repository

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import io.github.erikjhordanrey.arch_components_paging_library.data.remote.MoviesRemoteDataSource
import io.github.erikjhordanrey.arch_components_paging_library.data.room.DATABASE.PAGE_SIZE
import io.github.erikjhordanrey.arch_components_paging_library.data.room.Movie
import io.github.erikjhordanrey.arch_components_paging_library.data.room.MoviesRoomDataSource
import io.reactivex.Observable

class MoviesRepository(private val moviesRemoteDataSource: MoviesRemoteDataSource, private val moviesRoomDataSource: MoviesRoomDataSource) {
    private val pageListMovieBoundaryCallback = PageListMovieBoundaryCallback(moviesRemoteDataSource, moviesRoomDataSource)

    fun fetchOrGetMovies(): Observable<PagedList<Movie>> = RxPagedListBuilder(moviesRoomDataSource.getMovies(), PAGE_SIZE)
            .setBoundaryCallback(PageListMovieBoundaryCallback(moviesRemoteDataSource, moviesRoomDataSource))
            .buildObservable()

    fun disposable() = pageListMovieBoundaryCallback.disposable
}
