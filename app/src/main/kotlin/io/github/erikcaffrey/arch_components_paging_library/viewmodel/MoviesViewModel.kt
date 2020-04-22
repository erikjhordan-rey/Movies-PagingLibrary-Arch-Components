package io.github.erikcaffrey.arch_components_paging_library.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import io.github.erikcaffrey.arch_components_paging_library.data.repository.MoviesRepository
import io.github.erikcaffrey.arch_components_paging_library.data.room.Movie
import io.reactivex.disposables.CompositeDisposable

class MoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val _pagedListMovie = MutableLiveData<PagedList<Movie>>()
    var pagedListMovie = _pagedListMovie

    fun getMovies() {
        compositeDisposable.add(moviesRepository.fetchOrGetMovies().subscribe({ _pagedListMovie.value = it }, { it.printStackTrace() }))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
