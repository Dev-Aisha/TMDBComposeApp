package com.example.tmdbccomposeapp.domain.onBoarding

import com.example.tmdbccomposeapp.data.dataStoreMovie.MovieAppDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetIsSafeFromDataStoreUseCase @Inject constructor(
    private val dataStore: MovieAppDataStore
)
{
    suspend operator fun  invoke(): Flow<Boolean> {
        return dataStore.readOnBoardingState()

    }


}