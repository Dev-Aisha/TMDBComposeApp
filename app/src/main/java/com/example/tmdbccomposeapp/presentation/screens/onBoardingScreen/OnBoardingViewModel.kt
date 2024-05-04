package com.example.tmdbccomposeapp.presentation.screens.onBoardingScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbccomposeapp.domain.onBoarding.GetIsSafeFromDataStoreUseCase
import com.example.tmdbccomposeapp.domain.onBoarding.SaveIsFirstTimeOnDataStoreUseCase
import com.example.tmdbccomposeapp.presentation.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(

    private val saveIsFirstTimeOnDataStoreUseCase: SaveIsFirstTimeOnDataStoreUseCase,
    private val getIsSafeFromDataStoreUseCase: GetIsSafeFromDataStoreUseCase
): ViewModel() {

    val onBoardingCompleted = MutableStateFlow(false)
    var startDestination: String = Screens.Onboarding.route

    init {
        getOnBoardingState()
    }

    private fun getOnBoardingState() {
        viewModelScope.launch {
            onBoardingCompleted.value = getIsSafeFromDataStoreUseCase().stateIn(viewModelScope).value
            startDestination =
                if(onBoardingCompleted.value) Screens.Home.route else Screens.Onboarding.route
        }
    }

    fun saveOnBoardingState(showOnBoardingPage: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            saveIsFirstTimeOnDataStoreUseCase(showTipsPage = showOnBoardingPage)
        }
    }
}

