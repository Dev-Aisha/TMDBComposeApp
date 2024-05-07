package com.example.tmdbccomposeapp.presentation.screens.SearchScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.tmdbccomposeapp.domain.Search.SearchMovieUseCase
import com.example.tmdbccomposeapp.model.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val searchMovieUseCase: SearchMovieUseCase
): ViewModel(){

    var searchMovieState: MutableStateFlow<PagingData<Results>> =
        MutableStateFlow(PagingData.empty())


    fun getSearch(query: String){
        viewModelScope.launch {
            searchMovieUseCase.invoke(query).distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect {
                    searchMovieState.value = it
                }
        }
    }
}