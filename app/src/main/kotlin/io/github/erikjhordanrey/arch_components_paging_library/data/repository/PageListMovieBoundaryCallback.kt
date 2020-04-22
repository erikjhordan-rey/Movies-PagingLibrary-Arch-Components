package io.github.erikjhordanrey.arch_components_paging_library.data.repository

import android.util.Log
import androidx.paging.PagedList
import io.github.erikjhordanrey.arch_components_paging_library.data.remote.MoviesRemoteDataSource
import io.github.erikjhordanrey.arch_components_paging_library.data.remote.toMovieEntity
import io.github.erikjhordanrey.arch_components_paging_library.data.room.Movie
import io.github.erikjhordanrey.arch_components_paging_library.data.room.MoviesRoomDataSource
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PageListMovieBoundaryCallback(private val moviesRemoteDataSource: MoviesRemoteDataSource,
                                    private val moviesRoomDataSource: MoviesRoomDataSource) : PagedList.BoundaryCallback<Movie>() {

    private var isRequestRunning = false
    private var requestedPage = 1
    lateinit var disposable: Disposable
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
        disposable = moviesRemoteDataSource.fetchMovies(requestedPage)
                .map { movieApiList -> movieApiList.map { it.toMovieEntity() } }
                .doOnSuccess { listMovie ->
                    if (listMovie.isNotEmpty()) {
                        moviesRoomDataSource.storeMovies(listMovie)
                        Log.i(TAG, "Inserted: ${listMovie.size}")
                    } else {
                        Log.i(TAG, "No Inserted")
                    }
                    requestedPage++
                }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .ignoreElement()
                .doFinally { isRequestRunning = false }
                .subscribe({ Log.i(TAG, "Movies Completed") }, { it.printStackTrace() })

    }

    companion object {
        private const val TAG: String = "PageListMovieBoundary "
    }
}
