package com.example.movieapp.data.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movieapp.core.CommonUtils
import com.example.movieapp.data.repository.local.daos.MovieDao
import com.example.movieapp.data.repository.local.daos.UserDao
import com.example.movieapp.data.repository.local.entities.MovieEntity
import com.example.movieapp.data.repository.local.entities.UserEntity

@Database(entities = [MovieEntity::class, UserEntity::class], version = 1, exportSchema = false)
@TypeConverters(CommonUtils.CastConverters::class, CommonUtils.MovieCoverters::class)
abstract class MovieDB : RoomDatabase() {

    abstract fun moviedao(): MovieDao
    abstract fun userdao(): UserDao

    companion object {
        @Volatile
        private var Instance: MovieDB? = null

        fun getDatabase(context: Context): MovieDB {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    MovieDB::class.java,
                    "app_database"
                ).build()
                Instance = instance

                instance
            }
        }
    }
}