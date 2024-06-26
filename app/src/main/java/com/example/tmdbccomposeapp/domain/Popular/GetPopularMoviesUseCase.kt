package com.example.tmdbccomposeapp.domain.Popular

import androidx.paging.PagingData
import com.example.tmdbccomposeapp.data.repository.PopularMoviesRepository
import com.example.tmdbccomposeapp.model.Results
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Reusable
class GetPopularMoviesUseCase @Inject constructor(private val popularMoviesRepository: PopularMoviesRepository) {
     operator fun invoke(): Flow<PagingData<Results>> {
        return popularMoviesRepository.getPopularMovies()
    }

}