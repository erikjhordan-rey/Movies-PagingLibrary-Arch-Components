package io.github.erikjhordanrey.arch_components_paging_library.di

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import io.github.erikjhordanrey.arch_components_paging_library.data.remote.MoviesRemoteDataSource
import io.github.erikjhordanrey.arch_components_paging_library.data.remote.getService
import io.github.erikjhordanrey.arch_components_paging_library.data.repository.MoviesRepository
import io.github.erikjhordanrey.arch_components_paging_library.data.room.MoviesDatabase
import io.github.erikjhordanrey.arch_components_paging_library.data.room.MoviesRoomDataSource
import io.github.erikjhordanrey.arch_components_paging_library.viewmodel.MoviesViewModel
import io.github.erikjhordanrey.arch_components_paging_library.viewmodel.MoviesViewModelFactory

private fun provideMoviesDatabase(context: Context): MoviesRoomDataSource =
        MoviesRoomDataSource(MoviesDatabase.getInstance(context).movieDao())

private fun provideMoviesRepository(context: Context) =
        MoviesRepository(MoviesRemoteDataSource(getService()), provideMoviesDatabase(context))

private fun provideMoviesViewModelFactory(context: Context): ViewModelProvider.Factory =
        MoviesViewModelFactory(provideMoviesRepository(context))

fun provideMoviesViewModel(appCompatActivity: AppCompatActivity) =
        ViewModelProvider(appCompatActivity, provideMoviesViewModelFactory(appCompatActivity)).get(MoviesViewModel::class.java)

