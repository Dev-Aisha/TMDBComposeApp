package com.example.tmdbccomposeapp.domain.onBoarding

import com.example.tmdbccomposeapp.data.dataStoreMovie.MovieAppDataStore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveIsFirstTimeOnDataStoreUseCase @Inject constructor(
    private val dataStore: MovieAppDataStore
)
{
    suspend operator fun invoke(showTipsPage: Boolean)
    {
        dataStore.saveOnBoardingState(showTipsPage = showTipsPage)
    }

}