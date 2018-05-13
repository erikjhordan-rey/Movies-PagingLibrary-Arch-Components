package io.github.erikcaffrey.arch_components_paging_library.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import io.github.erikcaffrey.arch_components_paging_library.data.repository.MoviesRepository
import io.github.erikcaffrey.arch_components_paging_library.data.room.Movie

class MoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    var pagedListMovie = MutableLiveData<PagedList<Movie>>()

    fun getMovies() {
        moviesRepository.fetchOrGetMovies().subscribe({ pagedListMovie.value = it }, { it.printStackTrace() })
    }
}
