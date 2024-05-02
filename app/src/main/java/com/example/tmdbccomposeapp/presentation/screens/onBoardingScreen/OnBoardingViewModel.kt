package com.example.tmdbccomposeapp.presentation.screens.onBoardingScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbccomposeapp.domain.onBoarding.GetIsSafeFromDataStoreUseCase
import com.example.tmdbccomposeapp.domain.onBoarding.SaveIsFirstTimeonDataStoreUseCase
import com.example.tmdbccomposeapp.presentation.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(

    private val saveIsFirstTimeonDataStoreUseCase: SaveIsFirstTimeonDataStoreUseCase,
    private val getIsSafeFromDataStoreUseCase: GetIsSafeFromDataStoreUseCase
): ViewModel() {

    val onboardibgCompleted = MutableStateFlow(false)
    var startDestination: String = Screens.Home.route
    init {
        getOnBoardingState()
    }

    private fun getOnBoardingState() {
        viewModelScope.launch {
            onboardibgCompleted.value= getIsSafeFromDataStoreUseCase().stateIn(viewModelScope).value
            startDestination =
                if(onboardibgCompleted.value) Screens.Home.route else Screens.Onboarding.route
        }
    }

    fun saveOnBoardingState(showOnBoardingPage: Boolean) {
        viewModelScope.launch {
            saveIsFirstTimeonDataStoreUseCase(showTipsPage = showOnBoardingPage)
        }
    }
}

