package com.example.tmdbccomposeapp.data.repository

import com.example.tmdbccomposeapp.data.remote.MovieApi
import com.example.tmdbccomposeapp.model.SearchResponse
import com.example.tmdbccomposeapp.model.UIState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopularMoviesRepository @Inject constructor(
    val moviesApi: MovieApi,
) {
    suspend fun getPopularMovies(): UIState<SearchResponse>{
        try{
            val response = moviesApi.getUpcoming()
            if(response.isSuccessful && response.body() != null){
                    return UIState.Success(response.body())
                }else{
                    return UIState.Empty(message = response.message().toString())
                }
        }catch (e: Exception){
            return UIState.Error(e.message.toString())
        }
        
    }
}