package io.github.erikcaffrey.arch_components_paging_library.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import io.github.erikcaffrey.arch_components_paging_library.data.repository.MoviesRepository
import io.github.erikcaffrey.arch_components_paging_library.data.room.Movie
import io.reactivex.disposables.CompositeDisposable

class MoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    var pagedListMovie = MutableLiveData<PagedList<Movie>>()

    fun getMovies() {
        compositeDisposable.add(moviesRepository.fetchOrGetMovies()
                .subscribe({ pagedListMovie.value = it }, { it.printStackTrace() }))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
