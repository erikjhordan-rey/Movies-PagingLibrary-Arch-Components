package io.github.erikcaffrey.arch_components_paging_library.data.remote

import io.github.erikcaffrey.arch_components_paging_library.data.remote.Endpoint.DISCOVER
import io.github.erikcaffrey.arch_components_paging_library.data.remote.Query.API_KEY
import io.github.erikcaffrey.arch_components_paging_library.data.remote.Query.API_KEY_VALUE
import io.github.erikcaffrey.arch_components_paging_library.data.remote.Query.INCLUDE_ADULT
import io.github.erikcaffrey.arch_components_paging_library.data.remote.Query.INCLUDE_ADULT_DEFAULT
import io.github.erikcaffrey.arch_components_paging_library.data.remote.Query.LANGUAGE
import io.github.erikcaffrey.arch_components_paging_library.data.remote.Query.LANGUAGE_DEFAULT
import io.github.erikcaffrey.arch_components_paging_library.data.remote.Query.PAGE
import io.github.erikcaffrey.arch_components_paging_library.data.remote.Query.SORT_BY
import io.github.erikcaffrey.arch_components_paging_library.data.remote.Query.SORT_BY_DEFAULT
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET(DISCOVER)
    fun fetchMovies(@Query(PAGE) page: Int,
                    @Query(SORT_BY) sortBy: String = SORT_BY_DEFAULT,
                    @Query(LANGUAGE) language: String = LANGUAGE_DEFAULT,
                    @Query(INCLUDE_ADULT) includeAdult: Boolean = INCLUDE_ADULT_DEFAULT,
                    @Query(API_KEY) apiKey: String = API_KEY_VALUE): Single<List<MovieApi>>
}
