package com.example.tmdbccomposeapp.domain.User

import com.example.tmdbccomposeapp.data.repository.UserRepository
import javax.inject.Inject

class getUserAccountUseCase @Inject constructor(private val UserMoviesRepository: UserRepository) {
    suspend operator fun invoke(sessionId: String)=
        UserMoviesRepository.getUserAccount(sessionId)

    }