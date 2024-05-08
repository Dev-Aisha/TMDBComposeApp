package com.example.tmdbccomposeapp.data.repository

import com.example.tmdbccomposeapp.data.remote.MovieApi
import com.example.tmdbccomposeapp.model.UIState
import com.example.tmdbccomposeapp.model.UserAccount
import com.example.tmdbccomposeapp.model.UserTokenResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    val moviesApi: MovieApi,
) {

    suspend fun getUserToken(): UIState<UserTokenResponse>{
        try{
            val response = moviesApi.getUserToken()
            if(response.isSuccessful && response.body() != null){
                    return UIState.Success(response.body())
                }else{
                    return UIState.Empty(message = response.message().toString())
                }
        }catch (e: Exception){
            return UIState.Error(e.message.toString())
        }

    }

    suspend fun getSessionId(requestToken: String): UIState<UserTokenResponse>{
        try{
            val response = moviesApi.getSessionId(requestToken= requestToken)
            if(response.isSuccessful && response.body() != null){
                return UIState.Success(response.body())
            }else{
                return UIState.Empty(message = response.message().toString())
            }
        }catch (e: Exception){
            return UIState.Error(e.message.toString())
        }

    }

    suspend fun getUserAccount(sessionId: String): UIState<UserAccount>{
        try{
            val response = moviesApi.getUserAccount(sessionId = sessionId)
            if(response.isSuccessful && response.body() != null){
                return UIState.Success(response.body())
            }else{
                return UIState.Empty(message = response.message().toString())
            }
        }catch (e: Exception){
            return UIState.Error(e.message.toString())
        }

    }

}