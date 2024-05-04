package com.example.tmdbccomposeapp.domain.Popular

import com.example.tmdbccomposeapp.data.repository.PopularMoviesRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetPopularMoviesUseCase @Inject constructor(
    private val popularMoviesRepository: PopularMoviesRepository) {
    suspend operator fun invoke() = popularMoviesRepository.getPopularMovies()

}