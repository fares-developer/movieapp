package com.example.movieapp.data.repository.local

import com.example.movieapp.core.CommonUtils
import com.example.movieapp.data.repository.local.daos.MovieDao
import com.example.movieapp.data.repository.local.entities.CastEntity
import com.example.movieapp.data.repository.local.entities.MovieAndDetails
import com.example.movieapp.data.repository.local.entities.MovieEntity
import com.example.movieapp.data.repository.remote.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class MovieRepoImpl(
    private val dao: MovieDao,
    private val remoterepo: MovieRepository
) : MovieRepo {
    override suspend fun putNowPlayingMovies(movies: List<MovieEntity>) {
        dao.putNowPlayingMovies(movies)
    }

    override suspend fun putUpcomingMovies(movies: List<MovieEntity>) {
        dao.putUpcomingMovies(movies)
    }

    override suspend fun putTopRatedMovies(movies: List<MovieEntity>) {
        dao.putTopRatedMovies(movies)
    }

    override suspend fun putPopularMovies(movies: List<MovieEntity>) {
        dao.putPopularMovies(movies)
    }

    override suspend fun putDetailsMovie(details: MovieEntity) {
        details.cast.forEach { putCast(it) }
        dao.putDetailsMovie(details)
    }

    override suspend fun putCast(cast: CastEntity) {
        dao.putCast(cast)
    }

    override suspend fun getNowPlayingMovies(groupmovie: String): Flow<List<MovieEntity>> {
        val movies = remoterepo.getNowPlayingMovies()
        putNowPlayingMovies(movies.results.map { CommonUtils.toMovieEntity(it, groupmovie) })
        return dao.getNowPlayingMovies(groupmovie)
    }

    override suspend fun getUpcomingMovies(groupmovie: String): Flow<List<MovieEntity>> {
        val movies = remoterepo.getUpcomingMovies()
        putUpcomingMovies(movies.results.map { CommonUtils.toMovieEntity(it, groupmovie) })
        return dao.getUpcomingMovies(groupmovie)
    }

    override suspend fun getTopRatedMovies(groupmovie: String): Flow<List<MovieEntity>> {
        val movies = remoterepo.getTopRatedMovies()
        putTopRatedMovies(movies.results.map { CommonUtils.toMovieEntity(it, groupmovie) })
        return dao.getTopRatedMovies(groupmovie)
    }

    override suspend fun getPopularMovies(groupmovie: String): Flow<List<MovieEntity>> {
        val movies = remoterepo.getPopularMovies()
        putPopularMovies(movies.results.map { CommonUtils.toMovieEntity(it, groupmovie) })
        return dao.getPopularMovies(groupmovie)
    }

    override suspend fun getDetailsMovie(id: Int, groupmovie: String): Flow<MovieAndDetails> {
        val movie = CommonUtils.toMovieEntity(
            remoterepo.getDetailsMovie(id),
            groupmovie
        )
        putDetailsMovie(movie)
        movie.cast = getCast().first().cast
        return dao.getDetailsMovie(movie.id)
    }

    override suspend fun getCast(): Flow<MovieAndDetails> {
        return dao.getCast()
    }

    override suspend fun deleteCast(cast: List<CastEntity>) {
        dao.deleteCast(cast)
    }
}