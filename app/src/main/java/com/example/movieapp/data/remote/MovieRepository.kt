package com.example.movieapp.data.remote

import com.example.movieapp.data.model.Credits
import com.example.movieapp.data.model.MovieModel
import com.example.movieapp.data.model.Results
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieRepository {

    suspend fun getUpcomingMovies(): Results

    suspend fun getTopRatedMovies(): Results

    suspend fun getPopularMovies(): Results

    suspend fun getDetailsMovie(idMovie: Int): MovieModel
}

class MovieRepoImplements(
    private val movieRepo: MovieApiService
) : MovieRepository {

    override suspend fun getUpcomingMovies(): Results = movieRepo.getUpcomingMovies()

    override suspend fun getTopRatedMovies(): Results = movieRepo.getTopRatedMovies()

    override suspend fun getPopularMovies(): Results = movieRepo.getPopularMovies()

    override suspend fun getDetailsMovie(idMovie: Int): MovieModel {
        return movieRepo.getDetailsMovie(idMovie = idMovie.toString()).apply {
            credits = movieRepo.getCredits(idMovie = idMovie.toString())
        }
    }
}
