package io.github.erikcaffrey.arch_components_paging_library.di

import androidx.lifecycle.ViewModelProvider
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import io.github.erikcaffrey.arch_components_paging_library.data.remote.MoviesRemoteDataSource
import io.github.erikcaffrey.arch_components_paging_library.data.remote.getService
import io.github.erikcaffrey.arch_components_paging_library.data.repository.MoviesRepository
import io.github.erikcaffrey.arch_components_paging_library.data.room.MoviesDatabase
import io.github.erikcaffrey.arch_components_paging_library.data.room.MoviesRoomDataSource
import io.github.erikcaffrey.arch_components_paging_library.viewmodel.MoviesViewModel
import io.github.erikcaffrey.arch_components_paging_library.viewmodel.MoviesViewModelFactory

object Injection {

    fun provideMoviesViewModel(appCompatActivity: AppCompatActivity) =
            ViewModelProviders.of(appCompatActivity, provideMoviesViewModelFactory(appCompatActivity)).get(MoviesViewModel::class.java)

    private fun provideMoviesViewModelFactory(context: Context) : ViewModelProvider.Factory =
            MoviesViewModelFactory(provideMoviesRepository(context))

    private fun provideMoviesRepository(context: Context) =
            MoviesRepository(MoviesRemoteDataSource(getService()), provideMoviesDatabase(context))

    private fun provideMoviesDatabase(context: Context): MoviesRoomDataSource =
            MoviesRoomDataSource(MoviesDatabase.getInstance(context).movieDao())

}
