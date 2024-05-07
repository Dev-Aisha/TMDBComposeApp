package com.example.tmdbccomposeapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.tmdbccomposeapp.data.paging.SearchPagingSource
import com.example.tmdbccomposeapp.data.remote.MovieApi
import com.example.tmdbccomposeapp.model.Results
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMoviesRepository @Inject constructor(
    val moviesApi: MovieApi,
) {
    fun searchMovies(query: String): Flow<PagingData<Results>>{
        return Pager(
            config = PagingConfig
                (pageSize = 15, prefetchDistance = 2),
            pagingSourceFactory = {
                SearchPagingSource(moviesApi, query)
            }
        ).flow
    }
}