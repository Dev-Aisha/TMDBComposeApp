package com.example.tmdbccomposeapp.data.remote

import com.example.tmdbccomposeapp.BuildConfig
import com.example.tmdbccomposeapp.model.MovieDetailsResponse
import com.example.tmdbccomposeapp.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi{
    @GET("3/movie/upcoming")
    suspend fun getUpcoming(
        @Query("api_key")
        apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language")
        language: String = "en-US",
        @Query("page")
        page: Int=1,
    ): Response<SearchResponse>

    @GET("3/movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id")
        movieId: Int,
        @Query("api_key")
        apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language")
        language: String = "en-US",
        @Query("append_to_response")
        append_to_response: String?=""

    ): Response<MovieDetailsResponse>

    @GET("3/search/multi")
    suspend fun searchMovie(
        @Query("query")
        query: String,
        @Query("api_key")
        apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("Language")
        language: String = "en-US",
        @Query("page")
        page: Int = 1,

        ): Response<SearchResponse>


}


