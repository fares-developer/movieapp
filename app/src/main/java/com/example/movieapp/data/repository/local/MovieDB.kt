package com.example.movieapp.data.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDB : RoomDatabase() {

    abstract fun moviedao(): MovieDao

    companion object {
        @Volatile
        private var Instance: MovieDB? = null

        fun getDatabase(context: Context): MovieDB {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, MovieDB::class.java, "movie_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}