package com.example.tmdbccomposeapp.domain.DetailsMovie



import com.example.tmdbccomposeapp.data.repository.DetailsMoviesRepository
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(private val detailsMoviesRepository: DetailsMoviesRepository) {
    suspend operator fun invoke(id: Int) = detailsMoviesRepository.getMovieDetails(id)

}
