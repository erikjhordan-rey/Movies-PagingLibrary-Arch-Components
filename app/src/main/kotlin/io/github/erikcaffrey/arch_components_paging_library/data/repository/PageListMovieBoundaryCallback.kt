package io.github.erikcaffrey.arch_components_paging_library.data.repository

import android.arch.paging.PagedList
import android.util.Log
import io.github.erikcaffrey.arch_components_paging_library.data.remote.MoviesRemoteDataSource
import io.github.erikcaffrey.arch_components_paging_library.data.remote.toMovieEntity
import io.github.erikcaffrey.arch_components_paging_library.data.room.Movie
import io.github.erikcaffrey.arch_components_paging_library.data.room.MoviesRoomDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PageListMovieBoundaryCallback(private val moviesRemoteDataSource: MoviesRemoteDataSource,
                                    private val moviesRoomDataSource: MoviesRoomDataSource) : PagedList.BoundaryCallback<Movie>() {

    private var isRequestRunning = false
    private var requestedPage = 1

    override fun onZeroItemsLoaded() {
        Log.i(TAG, "onZeroItemsLoaded")
        fetchAndStoreMovies()
    }

    override fun onItemAtEndLoaded(itemAtEnd: Movie) {
        Log.i(TAG, "onItemAtEndLoaded")
        fetchAndStoreMovies()
    }

    private fun fetchAndStoreMovies() {
        if (isRequestRunning) return

        isRequestRunning = true
        moviesRemoteDataSource.fetchMovies(requestedPage)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map { movieApiList -> movieApiList.map { it.toMovieEntity() } }
                .doOnSuccess { moviesRoomDataSource.storeMovies(it); requestedPage++ }
                .toCompletable()
                .doOnTerminate { isRequestRunning = false }
                .subscribe({ Log.i(TAG, "Movies Stored Successfully") }, { it.printStackTrace() })

    }

    companion object {
        private const val TAG: String = "PageListMovieBoundary "
    }
}
