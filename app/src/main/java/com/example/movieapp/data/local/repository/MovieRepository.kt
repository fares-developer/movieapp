package com.example.movieapp.data.local.repository

import com.example.movieapp.data.local.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getUpcomingMovies(type: String): Flow<List<MovieEntity>>

    suspend fun getTopRatedMovies(type: String): Flow<List<MovieEntity>>

    suspend fun getPopularMovies(type: String): Flow<List<MovieEntity>>

    suspend fun getDetailsMovie(idMovie: String): Flow<MovieEntity>
}