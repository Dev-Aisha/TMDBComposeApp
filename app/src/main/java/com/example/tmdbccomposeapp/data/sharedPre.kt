package com.example.tmdbccomposeapp.data

import android.content.Context
import android.content.SharedPreferences

object PreferencesManager {
    private const val PREF_NAME = "my_preferences"
    private const val KEY_FIRST_RUN = "first_run"

    fun isFirstRun(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(KEY_FIRST_RUN, true)
    }

    fun setFirstRun(context: Context, isFirstRun: Boolean) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putBoolean(KEY_FIRST_RUN, isFirstRun)
            apply()
        }
    }
}