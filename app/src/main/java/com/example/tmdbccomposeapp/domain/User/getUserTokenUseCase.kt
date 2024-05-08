package com.example.tmdbccomposeapp.domain.User

import androidx.paging.PagingData
import com.example.tmdbccomposeapp.data.repository.SearchMoviesRepository
import com.example.tmdbccomposeapp.data.repository.UserRepository
import com.example.tmdbccomposeapp.model.Results
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class getUserTokenUseCase @Inject constructor(private val UserMoviesRepository: UserRepository) {
    suspend operator fun invoke()=
        UserMoviesRepository.getUserToken()


}