package com.example.tmdbapp.common.di

import android.app.Application
import androidx.room.Room
import com.example.tmdbapp.feature.content.data.room.SaveRepository
import com.example.tmdbapp.feature.content.data.room.SaveRepositoryImpl
import com.example.tmdbapp.feature.content.data.room.TmdbSaveDao
import com.example.tmdbapp.feature.content.data.room.TmdbSavesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    fun provideTmdbSavesDatabase(app: Application): TmdbSavesDatabase {
        return Room.databaseBuilder(
            app.applicationContext,
            TmdbSavesDatabase::class.java,
            "tmdb_saves_movies.db"
            ).build()
    }

    @Provides
    fun provideTmdbSaveDao(tmdbSavesDatabase: TmdbSavesDatabase) = tmdbSavesDatabase.dao

    @Provides
    fun provideTmdbSaveRepository(tmdbSaveDao: TmdbSaveDao): SaveRepository = SaveRepositoryImpl(tmdbSaveDao)


}