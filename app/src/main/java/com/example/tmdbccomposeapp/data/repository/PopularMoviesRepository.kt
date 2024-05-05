package com.example.tmdbccomposeapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.tmdbccomposeapp.data.paging.MoviePagingSource
import com.example.tmdbccomposeapp.data.remote.MovieApi
import com.example.tmdbccomposeapp.model.Results
import com.example.tmdbccomposeapp.model.SearchResponse
import com.example.tmdbccomposeapp.model.UIState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopularMoviesRepository @Inject constructor(
    val moviesApi: MovieApi,
) {
//    suspend fun getPopularMovies(): UIState<SearchResponse>{
//        try{
//            val response = moviesApi.getUpcoming()
//            if(response.isSuccessful && response.body() != null){
//                    return UIState.Success(response.body())
//                }else{
//                    return UIState.Empty(message = response.message().toString())
//                }
//        }catch (e: Exception){
//            return UIState.Error(e.message.toString())
//        }
//
//    }

    fun getPopularMovies(): Flow<PagingData<Results>> {
        return Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            pagingSourceFactory = {
                MoviePagingSource(moviesApi)
            }
        ).flow
    }
}