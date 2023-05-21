package com.example.movieapp.data.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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
    suspend fun getNowPlayingMovies(groupmovie:String): Flow<List<MovieEntity>>

    @Query("select * from movies where groupmovie= :groupmovie")
    suspend fun getUpcomingMovies(groupmovie:String): Flow<List<MovieEntity>>

    @Query("select * from movies where groupmovie= :groupmovie")
    suspend fun getTopRatedMovies(groupmovie:String): Flow<List<MovieEntity>>

    @Query("select * from movies where groupmovie= :groupmovie")
    suspend fun getPopularMovies(groupmovie:String): Flow<List<MovieEntity>>

}