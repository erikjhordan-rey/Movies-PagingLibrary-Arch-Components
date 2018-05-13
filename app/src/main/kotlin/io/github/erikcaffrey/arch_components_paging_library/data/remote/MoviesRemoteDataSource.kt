package io.github.erikcaffrey.arch_components_paging_library.data.remote

class MoviesRemoteDataSource(private val moviesService: MoviesService) {

    fun fetchMovies(page: Int) = moviesService.fetchMovies(page)
}
