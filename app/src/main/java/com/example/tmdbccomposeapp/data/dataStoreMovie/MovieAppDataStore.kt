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


const val PREFFERENCES_NAME = "is_first_run"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "my_data_store")

class MovieAppDataStore(context: Context) {
    private val onBoardingScreenKwy = booleanPreferencesKey(name=PREFFERENCES_NAME)
    private val dataStore = context.dataStore

    suspend fun saveOnBoardingState(showTipsPage: Boolean){
        dataStore.edit { preferences ->
            preferences[onBoardingScreenKwy] = showTipsPage
        }
    }
    suspend fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[onBoardingScreenKwy] ?: false
        }

    }

}