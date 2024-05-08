package com.example.tmdbccomposeapp.domain.User

import com.example.tmdbccomposeapp.data.repository.UserRepository
import javax.inject.Inject

class getSessionIdUseCase @Inject constructor(private val UserRepository: UserRepository) {
        suspend operator fun invoke(requestToken: String) =
            UserRepository.getSessionId(requestToken)

    }
