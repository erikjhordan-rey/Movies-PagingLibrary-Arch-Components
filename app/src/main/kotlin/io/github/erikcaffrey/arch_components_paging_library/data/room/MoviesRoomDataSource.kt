package io.github.erikcaffrey.arch_components_paging_library.data.room

class MoviesRoomDataSource(private val moviesDao: MoviesDao) {

    fun storeMovies(movieList: List<Movie>) = moviesDao.insert(movieList)

    fun getMovies() = moviesDao.allMovies()
}
