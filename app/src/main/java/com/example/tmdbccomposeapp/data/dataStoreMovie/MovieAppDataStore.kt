package com.example.tmdbccomposeapp.data.dataStoreMovie

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


const val PREFERENCES_NAME = "is_first_run"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MovieAppDataStore(context: Context) {
    private val onBoardingScreenKey = booleanPreferencesKey(name=PREFERENCES_NAME)
    private val dataStore = context.dataStore

    suspend fun saveOnBoardingState(showTipsPage: Boolean){
        dataStore.edit { preferences ->
            preferences[onBoardingScreenKey] = showTipsPage
        }
    }
     fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data
            .map { preferences ->
            preferences[onBoardingScreenKey] ?: false
        }

    }

}