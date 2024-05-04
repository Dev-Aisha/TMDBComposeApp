package com.example.tmdbccomposeapp.data.remote

import com.example.tmdbccomposeapp.BuildConfig
import com.example.tmdbccomposeapp.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
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

}