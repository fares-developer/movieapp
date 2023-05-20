package com.example.movieapp.data.local.daos

import androidx.room.Dao
import androidx.room.Query
import com.example.movieapp.MovieTypes
import com.example.movieapp.data.local.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    //Selects
    @Query("select * from movies where movietypes = :type")
    suspend fun getUpcomingMovies(type: String = MovieTypes.UPCOMING.lower): Flow<List<MovieEntity>>

    @Query("select * from movies where movietypes = :type")
    suspend fun getTopRatedMovies(type: String = MovieTypes.TOPRATED.lower): Flow<List<MovieEntity>>

    @Query("select * from movies where movietypes = :type")
    suspend fun getPopularMovies(type: String = MovieTypes.POPULAR.lower): Flow<List<MovieEntity>>

    @Query("select * from movies where id = :idMovie")
    suspend fun getDetailsMovie(idMovie: String): Flow<MovieEntity>

    //Inserts

}