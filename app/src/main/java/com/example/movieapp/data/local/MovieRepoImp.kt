package com.example.movieapp.data.local

import com.example.movieapp.data.local.daos.MovieDao
import com.example.movieapp.data.local.entities.MovieEntity
import com.example.movieapp.data.local.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class MovieRepoImp(private val movieDao: MovieDao):MovieRepository {

    override suspend fun getUpcomingMovies(type: String): Flow<List<MovieEntity>> {
        return movieDao.getUpcomingMovies(type)
    }

    override suspend fun getTopRatedMovies(type: String): Flow<List<MovieEntity>> {
        return movieDao.getTopRatedMovies(type)
    }

    override suspend fun getPopularMovies(type: String): Flow<List<MovieEntity>> {
       return movieDao.getPopularMovies(type)
    }

    override suspend fun getDetailsMovie(idMovie: String): Flow<MovieEntity> {
        return movieDao.getDetailsMovie(idMovie)
    }
}