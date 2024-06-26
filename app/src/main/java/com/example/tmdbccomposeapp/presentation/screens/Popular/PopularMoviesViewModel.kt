package com.example.tmdbccomposeapp.presentation.screens.Popular

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.tmdbccomposeapp.domain.Popular.GetPopularMoviesUseCase
import com.example.tmdbccomposeapp.model.Results
import com.example.tmdbccomposeapp.model.SearchResponse
import com.example.tmdbccomposeapp.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase

): ViewModel() {
    var popularMoviesState: MutableStateFlow<PagingData<Results>> =
        MutableStateFlow(PagingData.empty())

    init{
        getArtWorks()
    }

    private fun getArtWorks() {
        viewModelScope.launch {
            getPopularMoviesUseCase.invoke().distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect{
                popularMoviesState.value = it
            }
//            when(val response = getPopularMoviesUseCase.invoke()){
//                is UIState.Success -> popularMoviesState.value = UIState.Success(response.data)
//                is UIState.Error -> popularMoviesState.value = UIState.Error(response.error)
//                is UIState.Empty -> popularMoviesState.value = UIState.Empty(title = response.title)
//                is UIState.Loading -> popularMoviesState.value = UIState.Loading()
//            }
        }
    }

}