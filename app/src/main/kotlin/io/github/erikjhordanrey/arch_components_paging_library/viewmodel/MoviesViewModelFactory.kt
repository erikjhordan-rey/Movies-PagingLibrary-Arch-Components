package io.github.erikjhordanrey.arch_components_paging_library.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.erikjhordanrey.arch_components_paging_library.data.repository.MoviesRepository

class MoviesViewModelFactory(private val moviesRepository: MoviesRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = MoviesViewModel(moviesRepository) as T
}
