package io.github.erikcaffrey.arch_components_paging_library.data.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import io.github.erikcaffrey.arch_components_paging_library.data.remote.Api.THE_MOVIE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

fun getService(): MoviesService = createRetrofit().create(MoviesService::class.java)

private fun createRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(THE_MOVIE_URL)
        .addConverterFactory(GsonConverterFactory.create(gsonDeserializer()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(provideOkHttpClient())
        .build()

private fun gsonDeserializer(): Gson = GsonBuilder()
        .registerTypeAdapter(object : TypeToken<List<@JvmSuppressWildcards MovieApi>>() {}.type, MoviesDeserializer<MovieApi>())
        .setLenient()
        .create()

fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
