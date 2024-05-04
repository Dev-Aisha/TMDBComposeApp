package com.example.tmdbccomposeapp.data.di

import android.content.Context
import com.example.tmdbccomposeapp.data.dataStoreMovie.MovieAppDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



    @Module
    @InstallIn(SingletonComponent::class)
    object DataStoreModule{

        @Provides
        @Singleton
        fun provideDataStoreOperations(
            @ApplicationContext context: Context
        )= MovieAppDataStore (context = context)
    }
