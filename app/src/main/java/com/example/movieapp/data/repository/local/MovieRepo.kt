package com.example.movieapp.data.repository.local

import kotlinx.coroutines.flow.Flow

interface MovieRepo {

    //Insert
    suspend fun putNowPlayingMovies(movies:List<MovieEntity>)

    suspend fun putUpcomingMovies(movies:List<MovieEntity>)

    suspend fun putTopRatedMovies(movies:List<MovieEntity>)

    suspend fun putPopularMovies(movies:List<MovieEntity>)

    //Select
    suspend fun getNowPlayingMovies(groupmovie:String): Flow<List<MovieEntity>>

    suspend fun getUpcomingMovies(groupmovie:String): Flow<List<MovieEntity>>

    suspend fun getTopRatedMovies(groupmovie:String): Flow<List<MovieEntity>>

    suspend fun getPopularMovies(groupmovie:String): Flow<List<MovieEntity>>
}