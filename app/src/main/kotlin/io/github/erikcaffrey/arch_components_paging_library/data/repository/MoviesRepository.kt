package io.github.erikcaffrey.arch_components_paging_library.data.repository

import android.arch.paging.PagedList
import android.arch.paging.RxPagedListBuilder
import io.github.erikcaffrey.arch_components_paging_library.data.remote.MoviesRemoteDataSource
import io.github.erikcaffrey.arch_components_paging_library.data.room.DATABASE.PAGE_SIZE
import io.github.erikcaffrey.arch_components_paging_library.data.room.Movie
import io.github.erikcaffrey.arch_components_paging_library.data.room.MoviesRoomDataSource
import io.reactivex.Observable

class MoviesRepository(private val moviesRemoteDataSource: MoviesRemoteDataSource, private val moviesRoomDataSource: MoviesRoomDataSource) {

    fun fetchOrGetMovies() : Observable<PagedList<Movie>> = RxPagedListBuilder(moviesRoomDataSource.getMovies(), PAGE_SIZE)
            .setBoundaryCallback(PageListMovieBoundaryCallback(moviesRemoteDataSource, moviesRoomDataSource))
            .buildObservable()
}
