package io.github.erikcaffrey.arch_components_paging_library.view

import androidx.lifecycle.Observer
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import io.github.erikcaffrey.arch_components_paging_library.R
import io.github.erikcaffrey.arch_components_paging_library.data.room.Movie
import io.github.erikcaffrey.arch_components_paging_library.di.Injection
import io.github.erikcaffrey.arch_components_paging_library.view.decorator.MarginDecoration
import io.github.erikcaffrey.arch_components_paging_library.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.activity_movies.movies_recycler

class MoviesActivity : AppCompatActivity() {

    private lateinit var moviesViewModel: MoviesViewModel
    private val moviePagedListAdapter by lazy { MoviesPagedListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        initRecycler()
        initViewModel()
        initObserver()
    }

    private fun initRecycler() {
        movies_recycler.apply {
            layoutManager = GridLayoutManager(this@MoviesActivity, 2)
            addItemDecoration(MarginDecoration(this@MoviesActivity))
            adapter = moviePagedListAdapter
        }
    }

    private fun initViewModel() {
        moviesViewModel = Injection.provideMoviesViewModel(this)
        moviesViewModel.getMovies()
    }

    private fun initObserver() {
        moviesViewModel.pagedListMovie.observe(this, Observer<PagedList<Movie>> {
            Log.d(MoviesActivity::class::java.name, "Movies: ${it?.size}")
            moviePagedListAdapter.submitList(it)
        })
    }
}
