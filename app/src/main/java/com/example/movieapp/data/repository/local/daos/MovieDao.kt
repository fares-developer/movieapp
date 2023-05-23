package com.example.movieapp.data.repository.local.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.movieapp.data.repository.local.entities.CastEntity
import com.example.movieapp.data.repository.local.entities.MovieAndDetails
import com.example.movieapp.data.repository.local.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    //Insert
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun putNowPlayingMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun putUpcomingMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun putTopRatedMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun putPopularMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun putDetailsMovie(details: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun putCast(cast: CastEntity)

    //Select
    @Query("select * from movies where groupmovie= :groupmovie")
    fun getNowPlayingMovies(groupmovie: String): Flow<List<MovieEntity>>

    @Query("select * from movies where groupmovie= :groupmovie")
    fun getUpcomingMovies(groupmovie: String): Flow<List<MovieEntity>>

    @Query("select * from movies where groupmovie= :groupmovie")
    fun getTopRatedMovies(groupmovie: String): Flow<List<MovieEntity>>

    @Query("select * from movies where groupmovie= :groupmovie")
    fun getPopularMovies(groupmovie: String): Flow<List<MovieEntity>>

    @Transaction
    @Query("select * from movies where id = :id")
    fun getDetailsMovie(id: Int): Flow<MovieAndDetails>

    @Transaction
    @Query("select * from movies")
    fun getCast(): Flow<MovieAndDetails>

    //Delete
    @Delete
    suspend fun deleteCast(cast: List<CastEntity>)
}