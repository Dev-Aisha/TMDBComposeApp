package com.example.tmdbccomposeapp.domain.Search

import androidx.paging.PagingData
import com.example.tmdbccomposeapp.data.repository.SearchMoviesRepository
import com.example.tmdbccomposeapp.model.Results
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@Reusable
class SearchMovieUseCase @Inject constructor(private val searchMoviesRepository: SearchMoviesRepository) {
    operator fun invoke(query: String): Flow<PagingData<Results>> {
        return searchMoviesRepository.searchMovies(query)
    }
}