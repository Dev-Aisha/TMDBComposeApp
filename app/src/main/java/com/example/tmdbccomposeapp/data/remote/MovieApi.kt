package com.example.tmdbccomposeapp.data.remote

import com.example.tmdbccomposeapp.BuildConfig
import com.example.tmdbccomposeapp.model.MovieDetailsResponse
import com.example.tmdbccomposeapp.model.SearchResponse
import com.example.tmdbccomposeapp.model.UserAccount
import com.example.tmdbccomposeapp.model.UserTokenResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
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

    @GET("3/authentication/token/new")
    suspend fun getUserToken(
        @Query("api_key")
        apiKey: String = BuildConfig.TMDB_API_KEY,

        ): Response<UserTokenResponse>

    @POST("3/authentication/session/new")
    suspend fun getSessionId(
        @Query("api_key")
        apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("request_token")
        requestToken: String,

        ): Response<UserTokenResponse>

    @GET("3/account")
    suspend fun getUserAccount(
        @Query("api_key")
        apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("session_id")
        sessionId: String,

        ): Response<UserAccount>




}


