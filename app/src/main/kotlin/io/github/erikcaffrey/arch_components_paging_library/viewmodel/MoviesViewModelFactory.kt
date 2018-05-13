package io.github.erikcaffrey.arch_components_paging_library.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import io.github.erikcaffrey.arch_components_paging_library.data.repository.MoviesRepository

class MoviesViewModelFactory(private val moviesRepository: MoviesRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = MoviesViewModel(moviesRepository) as T
}
