package com.example.movieapp.data.repository.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.data.repository.local.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    //Insert
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun putNowPlayingMovies(movies:List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun putUpcomingMovies(movies:List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun putTopRatedMovies(movies:List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun putPopularMovies(movies:List<MovieEntity>)

    //Select
    @Query("select * from movies where groupmovie= :groupmovie")
    fun getNowPlayingMovies(groupmovie:String): Flow<List<MovieEntity>>

    @Query("select * from movies where groupmovie= :groupmovie")
    fun getUpcomingMovies(groupmovie:String): Flow<List<MovieEntity>>

    @Query("select * from movies where groupmovie= :groupmovie")
    fun getTopRatedMovies(groupmovie:String): Flow<List<MovieEntity>>

    @Query("select * from movies where groupmovie= :groupmovie")
    fun getPopularMovies(groupmovie:String): Flow<List<MovieEntity>>

}