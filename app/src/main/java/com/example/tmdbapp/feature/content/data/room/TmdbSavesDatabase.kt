package com.example.tmdbapp.feature.content.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TmdbSave::class],
    version = 1,
    exportSchema = false
)
abstract class TmdbSavesDatabase: RoomDatabase() {
    abstract val dao: TmdbSaveDao
}