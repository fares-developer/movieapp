package com.example.movieapp.data.repository.local

import com.example.movieapp.data.repository.local.entities.CastEntity
import com.example.movieapp.data.repository.local.entities.MovieAndDetails
import com.example.movieapp.data.repository.local.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieRepo {

    //Insert
    suspend fun putNowPlayingMovies(movies: List<MovieEntity>)

    suspend fun putUpcomingMovies(movies: List<MovieEntity>)

    suspend fun putTopRatedMovies(movies: List<MovieEntity>)

    suspend fun putPopularMovies(movies: List<MovieEntity>)

    suspend fun putDetailsMovie(details: MovieEntity)

    suspend fun putCast(cast: CastEntity)

    //Select
    suspend fun getNowPlayingMovies(groupmovie: String): Flow<List<MovieEntity>>

    suspend fun getUpcomingMovies(groupmovie: String): Flow<List<MovieEntity>>

    suspend fun getTopRatedMovies(groupmovie: String): Flow<List<MovieEntity>>

    suspend fun getPopularMovies(groupmovie: String): Flow<List<MovieEntity>>

    suspend fun getDetailsMovie(id: Int, groupmovie: String): Flow<MovieAndDetails>

    suspend fun getCast(): Flow<MovieAndDetails>

    //Delete
    suspend fun deleteCast(cast: List<CastEntity>)
}