package com.example.movieapp.data.repository.local

import com.example.movieapp.data.repository.local.daos.MovieDao
import com.example.movieapp.data.repository.local.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

class MovieRepoImpl(private val moviedao: MovieDao) : MovieRepo {

    override suspend fun putNowPlayingMovies(movies: List<MovieEntity>) {
        moviedao.putNowPlayingMovies(movies)
    }

    override suspend fun putUpcomingMovies(movies: List<MovieEntity>) {
        moviedao.putUpcomingMovies(movies)
    }

    override suspend fun putTopRatedMovies(movies: List<MovieEntity>) {
        moviedao.putTopRatedMovies(movies)
    }

    override suspend fun putPopularMovies(movies: List<MovieEntity>) {
        moviedao.putPopularMovies(movies)
    }

    override suspend fun getNowPlayingMovies(groupmovie: String): Flow<List<MovieEntity>> {
        return moviedao.getNowPlayingMovies(groupmovie)
    }

    override suspend fun getUpcomingMovies(groupmovie: String): Flow<List<MovieEntity>> {
        return moviedao.getUpcomingMovies(groupmovie)
    }

    override suspend fun getTopRatedMovies(groupmovie: String): Flow<List<MovieEntity>> {
        return moviedao.getTopRatedMovies(groupmovie)
    }

    override suspend fun getPopularMovies(groupmovie: String): Flow<List<MovieEntity>> {
        return moviedao.getPopularMovies(groupmovie)
    }
}